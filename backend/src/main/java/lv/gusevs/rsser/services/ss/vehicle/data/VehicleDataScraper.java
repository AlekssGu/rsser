package lv.gusevs.rsser.services.ss.vehicle.data;

import lv.gusevs.rsser.services.common.vehicle.data.Vehicle;
import lv.gusevs.rsser.services.common.vehicle.data.VehicleDataService;
import lv.gusevs.rsser.utilities.XmlReader;
import org.dom4j.Document;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class VehicleDataScraper {

    private static final String BMW_5SERIES_RSS_FEED = "https://www.ss.lv/lv/transport/cars/bmw/5-series/sell/rss/";
    private static final String BMW_3SERIES_RSS_FEED = "https://www.ss.lv/lv/transport/cars/bmw/3-series/sell/rss/";

    private final XmlReader xmlReader;
    private final VehicleDataParser vehicleDataParser;
    private final VehicleDataService vehicleDataService;

    @Value("#{new Boolean('${application.scraper.ss.vehicle.enabled}')}")
    private boolean vehicleScraperEnabled;

    @Autowired
    VehicleDataScraper(XmlReader xmlReader,
                       VehicleDataParser vehicleDataParser,
                       VehicleDataService vehicleDataService) {
        this.xmlReader = xmlReader;
        this.vehicleDataParser = vehicleDataParser;
        this.vehicleDataService = vehicleDataService;
    }

    public void scrapeNewAds() {
        if (vehicleScraperEnabled) {
            Stream
                    .of(BMW_3SERIES_RSS_FEED, BMW_5SERIES_RSS_FEED)
                    .forEach(this::scrapeNewAds);
        }
    }

    private void scrapeNewAds(String feed) {
        Document document = xmlReader.of(feed).parseSilently();
        List<Node> nodes = document.selectNodes("/rss/channel/item");
        for (Node node : nodes) {
            getAndSaveNewVehicleOf(node);
        }
    }

    private void getAndSaveNewVehicleOf(Node node) {
        Vehicle vehicle = vehicleDataParser.getVehicle(node);
        if (isNew(vehicle)) {
            vehicleDataService.save(vehicle);
        }
    }

    private boolean isNew(Vehicle vehicle) {
        return vehicleDataService.isNew(vehicle);
    }

}
