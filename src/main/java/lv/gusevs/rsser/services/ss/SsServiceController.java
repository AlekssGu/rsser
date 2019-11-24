package lv.gusevs.rsser.services.ss;

import lv.gusevs.rsser.utilities.XmlReader;
import org.dom4j.Document;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SsServiceController {

    private static final String PASSAT_B6_RSS_FEED = "https://www.ss.lv/lv/transport/cars/volkswagen/passat-b6/sell/rss/";
    private static final String BMW_3SERIES_RSS_FEED = "https://www.ss.lv/lv/transport/cars/bmw/3-series/sell/rss/";
    private static final String ALL_CARS_RSS_FEED = "https://www.ss.lv/lv/transport/cars/rss/";

    private XmlReader xmlReader;
    private VehicleDataScraper vehicleDataScraper;
    private SsNotificationSender ssNotificationSender;

    @Autowired
    public SsServiceController(XmlReader xmlReader, VehicleDataScraper vehicleDataScraper, SsNotificationSender ssNotificationSender) {
        this.xmlReader = xmlReader;
        this.vehicleDataScraper = vehicleDataScraper;
        this.ssNotificationSender = ssNotificationSender;
    }

    @GetMapping("/ss/cars")
    public List<Vehicle> scrapeVehicles() {
        Document document = xmlReader.of(ALL_CARS_RSS_FEED).parseSilently();
        List<Node> nodes = document.selectNodes("/rss/channel/item");
        List<Vehicle> vehicles = new ArrayList<>();
        for (Node node : nodes) {
            Vehicle vehicle = vehicleDataScraper.getVehicle(node);
            if (vehicleImageIsAvailable(vehicle)) {
                vehicles.add(vehicle);
                notifyIfBmw(vehicle);
            }
        }
        return vehicles;
    }

    private void notifyIfBmw(Vehicle vehicle) {
        if ("BMW".equals(vehicle.getMake().toUpperCase())) {
            ssNotificationSender.sendNotification(vehicle);
        }
    }

    private boolean vehicleImageIsAvailable(Vehicle vehicle) {
        return vehicle.getImageUrl() != null && vehicle.getImageUrl().length() > 0;
    }
}
