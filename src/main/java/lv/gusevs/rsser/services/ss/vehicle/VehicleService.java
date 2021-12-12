package lv.gusevs.rsser.services.ss.vehicle;

import lv.gusevs.rsser.services.ss.vehicle.data.Vehicle;
import lv.gusevs.rsser.services.ss.vehicle.data.VehicleDataService;
import lv.gusevs.rsser.utilities.XmlReader;
import org.dom4j.Document;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Singleton
@Service
public class VehicleService {

    private static final String BMW_5SERIES_RSS_FEED = "https://www.ss.lv/lv/transport/cars/bmw/5-series/sell/rss/";
    private static final String BMW_3SERIES_RSS_FEED = "https://www.ss.lv/lv/transport/cars/bmw/3-series/sell/rss/";

    @Value("#{new Boolean('${system.vehicle_scraper_enabled}')}")
    private boolean vehicleScraperEnabled;

    private final XmlReader xmlReader;
    private final VehicleDataParser vehicleDataScraper;
    private final VehicleDataService vehicleDataService;

    @Autowired
    VehicleService(XmlReader xmlReader,
                   VehicleDataParser vehicleDataScraper,
                   VehicleDataService vehicleDataService) {
        this.xmlReader = xmlReader;
        this.vehicleDataScraper = vehicleDataScraper;
        this.vehicleDataService = vehicleDataService;
    }

    public List<Vehicle> newAds() {
        scrapeNewAds(); // TODO Run this in background, scrape new ads and save to the database
        return vehicleScraperEnabled ? getNewAds() : Collections.emptyList();
    }

    public void scrapeNewAds() {
        Stream
                .of(BMW_3SERIES_RSS_FEED, BMW_5SERIES_RSS_FEED)
                .forEach(this::scrapeNewAds);
    }

    private List<Vehicle> getNewAds() {
        return vehicleDataService.getLatest();
    }

    private void scrapeNewAds(String feed) {
        Document document = xmlReader.of(feed).parseSilently();
        List<Node> nodes = document.selectNodes("/rss/channel/item");
        for (Node node : nodes) {
            getAndSaveNewVehicleOf(node);
        }
    }

    private void getAndSaveNewVehicleOf(Node node) {
        Vehicle vehicle = vehicleDataScraper.getVehicle(node);
        if (isNew(vehicle)) {
            vehicleDataService.save(vehicle);
        }
    }

    private boolean isNew(Vehicle vehicle) {
        return vehicleDataService.isNew(vehicle);
    }

}
