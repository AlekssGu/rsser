package lv.gusevs.rsser.services.ss.vehicle.wheels.data;

import lv.gusevs.rsser.services.ss.vehicle.wheels.VehicleWheel;
import lv.gusevs.rsser.utilities.XmlReader;
import org.dom4j.Document;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class VehicleWheelDataScraper {

    private static final String BMW_WHEELS_RSS_FEED = "https://www.ss.lv/lv/transport/spare-parts/bmw/disks/19/sell/rss/";

    private final XmlReader xmlReader;
    private final VehicleWheelDataParser vehicleWheelDataParser;
    private final VehicleWheelDataService vehicleWheelDataService;

    @Autowired
    VehicleWheelDataScraper(XmlReader xmlReader,
                            VehicleWheelDataParser vehicleWheelDataParser,
                            VehicleWheelDataService vehicleWheelDataService) {
        this.xmlReader = xmlReader;
        this.vehicleWheelDataParser = vehicleWheelDataParser;
        this.vehicleWheelDataService = vehicleWheelDataService;
    }

    public void scrapeNewAds() {
        Stream.of(BMW_WHEELS_RSS_FEED)
                        .forEach(this::getAndSaveNewVehicleWheels);
    }

    private void getAndSaveNewVehicleWheels(String feed) {
        Document document = xmlReader.of(feed).parseSilently();
        List<Node> nodes = document.selectNodes("/rss/channel/item");
        for (Node node : nodes) {
            VehicleWheel vehicleWheel = vehicleWheelDataParser.getVehicleWheel(node);
            if (isNew(vehicleWheel)) {
                vehicleWheelDataService.save(vehicleWheel);
            }
        }
    }

    private boolean isNew(VehicleWheel vehicleWheel) {
        return vehicleWheelDataService.isNew(vehicleWheel);
    }
}
