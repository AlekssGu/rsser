package lv.gusevs.rsser.services.ss;

import lv.gusevs.rsser.utilities.XmlReader;
import org.dom4j.Document;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
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
        List<Vehicle> vehicles = serializeDataIn();
        for (Node node : nodes) {
            Vehicle vehicle = vehicleDataScraper.getVehicle(node);
            if (vehicleIsNotEmpty(vehicle) && isNewVehicle(vehicles, vehicle)) {
                notifyIfCarMatchesCriteria(vehicle);
                vehicles.add(vehicle);
            }
        }

        serializeDataOut(vehicles);

        vehicles.sort(Comparator.comparing(Vehicle::getDatePublished).reversed());
        return vehicles;
    }

    private boolean isNewVehicle(List<Vehicle> vehicles, Vehicle vehicle) {
        return vehicles.stream().noneMatch(storedVehicle -> storedVehicle.equals(vehicle));
    }

    private static void serializeDataOut(List<Vehicle> vehicles) {
        try {
            String fileName = "vehicles";
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(vehicles);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Vehicle> serializeDataIn() {
        List<Vehicle> vehicles = new ArrayList<>();
        try {
            String fileName = "vehicles";
            FileInputStream fin = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fin);
            vehicles = (ArrayList<Vehicle>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    private void notifyIfCarMatchesCriteria(Vehicle vehicle) {
        if (isBMW(vehicle) || isOpelAscona(vehicle)) {
            ssNotificationSender.sendNotification(vehicle);
        }
    }

    private boolean isOpelAscona(Vehicle vehicle) {
        return "OPEL".equals(vehicle.getMake().toUpperCase()) && "ASCONA".equals(vehicle.getModel().toUpperCase());
    }

    private boolean isBMW(Vehicle vehicle) {
        return "BMW".equals(vehicle.getMake().toUpperCase());
    }

    private boolean vehicleIsNotEmpty(Vehicle vehicle) {
        return vehicle.getImageUrl() != null && vehicle.getImageUrl().length() > 0
                && vehicle.getDatePublished() != null
                && vehicle.getDescription() != null && !vehicle.getDescription().toLowerCase().contains("pērku");
    }
}
