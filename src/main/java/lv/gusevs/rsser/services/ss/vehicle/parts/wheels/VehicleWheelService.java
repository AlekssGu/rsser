package lv.gusevs.rsser.services.ss.vehicle.parts.wheels;

import com.google.common.eventbus.EventBus;
import lv.gusevs.rsser.data.serialized.DataSerializationService;
import lv.gusevs.rsser.event.subscribers.Notification;
import lv.gusevs.rsser.services.ss.vehicle.parts.VehiclePart;
import lv.gusevs.rsser.utilities.XmlReader;
import org.dom4j.Document;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import java.util.Comparator;
import java.util.List;

@Singleton
@Service
public class VehicleWheelService {

	private static final String BMW_WHEELS_RSS_FEED = "https://www.ss.lv/lv/transport/spare-parts/bmw/disks/19/sell/rss/";
	private static final String FILENAME_VEHICLE_NAMES = "vehicleWheels";

	private final XmlReader xmlReader;
	private final DataSerializationService dataSerializationService;
	private final VehicleWheelDataParser vehicleWheelDataParser;
	private final EventBus eventBus;

	@Autowired
	VehicleWheelService(XmlReader xmlReader,
						DataSerializationService dataSerializationService,
						VehicleWheelDataParser vehicleWheelDataParser,
						EventBus eventBus) {
		this.xmlReader = xmlReader;
		this.dataSerializationService = dataSerializationService;
		this.vehicleWheelDataParser = vehicleWheelDataParser;
		this.eventBus = eventBus;
	}

	public List<VehiclePart> getAndNotify() {
		return retrieveAndNotifyFrom(BMW_WHEELS_RSS_FEED);
	}

	private List<VehiclePart> retrieveAndNotifyFrom(String feed) {
		Document document = xmlReader.of(feed).parseSilently();
		List<Node> nodes = document.selectNodes("/rss/channel/item");
		List<VehiclePart> vehicleWheels = dataSerializationService.serializeDataIn(FILENAME_VEHICLE_NAMES);
		for (Node node : nodes) {
			VehiclePart vehicleWheel = vehicleWheelDataParser.parse(node);
			if (isNew(vehicleWheels, vehicleWheel)) {
				vehicleWheels.add(vehicleWheel);
				eventBus.post(notificationOf(vehicleWheel));
			}
		}
		dataSerializationService.serializeDataOut(vehicleWheels, FILENAME_VEHICLE_NAMES);
		vehicleWheels.sort(Comparator.comparing(VehiclePart::getDatePublished).reversed());
		return vehicleWheels;
	}

	private Notification notificationOf(VehiclePart vehicleWheel) {
		return Notification.builder()
				.subject("Jauni diski!")
				.message(messageOf(vehicleWheel))
				.action("\nApskatīt: " + vehicleWheel.getLink())
				.imageUrl(vehicleWheel.getImageUrl())
				.build();
	}

	private String messageOf(VehiclePart vehicleWheel) {
		return "Cena: " + vehicleWheel.getPrice() + "\n" +
				vehicleWheel.getDescription() + "\n" +
				vehicleWheel.getImageUrl();
	}

	private boolean isNew(List<VehiclePart> vehicleWheels, VehiclePart vehicleWheel) {
		return vehicleWheels.stream().noneMatch(storedObject -> storedObject.equals(vehicleWheel));
	}

}
