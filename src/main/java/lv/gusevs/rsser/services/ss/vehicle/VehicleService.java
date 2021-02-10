package lv.gusevs.rsser.services.ss.vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import javax.inject.Singleton;

import org.dom4j.Document;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.gusevs.rsser.configuration.SystemSwitch;
import lv.gusevs.rsser.data.serialized.DataSerializationService;
import lv.gusevs.rsser.services.ss.SsNotificationSender;
import lv.gusevs.rsser.utilities.XmlReader;

@Singleton
@Service
public class VehicleService {

	private static final String BMW_5SERIES_RSS_FEED = "https://www.ss.lv/lv/transport/cars/bmw/5-series/sell/rss/";
	private static final String BMW_3SERIES_RSS_FEED = "https://www.ss.lv/lv/transport/cars/bmw/3-series/sell/rss/";

	private static final long MAX_PRICE = 5500L;
	private static final long YEAR_FROM = 2003L;
	private static final long YEAR_TO = 2009L;

	private final XmlReader xmlReader;
	private final VehicleDataParser vehicleDataScraper;
	private final SsNotificationSender ssNotificationSender;
	private final DataSerializationService dataSerializationService;
	private final SystemSwitch systemSwitch;

	@Autowired
	VehicleService(XmlReader xmlReader,
			VehicleDataParser vehicleDataScraper,
			SsNotificationSender ssNotificationSender,
			DataSerializationService dataSerializationService,
			SystemSwitch systemSwitch) {
		this.xmlReader = xmlReader;
		this.vehicleDataScraper = vehicleDataScraper;
		this.ssNotificationSender = ssNotificationSender;
		this.dataSerializationService = dataSerializationService;
		this.systemSwitch = systemSwitch;
	}

	public List<Vehicle> getAndNotify() {
		return systemSwitch.vehicleScraperEnabled() ? processNewVehicles() : Collections.emptyList();
	}

	private List<Vehicle> processNewVehicles() {
		List<Vehicle> vehicles = new ArrayList<>();
		Stream.of(BMW_3SERIES_RSS_FEED, BMW_5SERIES_RSS_FEED)
				.forEach(feed -> vehicles.addAll(retrieveAndNotifyFrom(feed)));
		return vehicles;
	}

	private List<Vehicle> retrieveAndNotifyFrom(String feed) {
		Document document = xmlReader.of(feed).parseSilently();
		List<Node> nodes = document.selectNodes("/rss/channel/item");
		List<Vehicle> vehicles = dataSerializationService.serializeDataIn("vehicles");
		for (Node node : nodes) {
			Vehicle vehicle = vehicleDataScraper.getVehicle(node);
			if (isNewVehicle(vehicles, vehicle)) {
				vehicles.add(vehicle);
				if (shouldNotifyAbout(vehicle)) {
					ssNotificationSender.sendNotification(vehicle);
				}
			}
		}
		dataSerializationService.serializeDataOut(vehicles, "vehicles");
		vehicles.sort(Comparator.comparing(Vehicle::getDatePublished).reversed());
		return vehicles;
	}

	private boolean shouldNotifyAbout(Vehicle vehicle) {
		return vehiclePriceInRange(vehicle, MAX_PRICE) &&
				vehicleYearInRange(vehicle, YEAR_FROM, YEAR_TO);
	}

	private boolean vehicleYearInRange(Vehicle vehicle, long yearFrom, long yearTo) {
		long vehicleMakeYear = Long.parseLong(vehicle.getMakeYear());
		return vehicleMakeYear >= yearFrom && vehicleMakeYear <= yearTo;
	}

	private boolean vehiclePriceInRange(Vehicle vehicle, long maxPrice) {
		String vehiclePrice = vehicle.getPrice().replace(",", "");
		vehiclePrice = vehiclePrice.replace(" ", "");
		long price = Long.parseLong(vehiclePrice);
		return price <= maxPrice;
	}

	private boolean isNewVehicle(List<Vehicle> vehicles, Vehicle vehicle) {
		return vehicles.stream().noneMatch(storedVehicle -> storedVehicle.equals(vehicle));
	}

}
