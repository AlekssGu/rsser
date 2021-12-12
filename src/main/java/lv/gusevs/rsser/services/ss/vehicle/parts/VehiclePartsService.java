package lv.gusevs.rsser.services.ss.vehicle.parts;

import lv.gusevs.rsser.services.ss.vehicle.parts.wheels.VehicleWheelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;

@Singleton
@Service
public class VehiclePartsService {

	private final VehicleWheelService vehicleWheelService;

	@Value("#{new Boolean('${system.vehicle_wheel_scraper_enabled}')}")
	private boolean vehicleWheelScraperEnabled;

	@Autowired
	VehiclePartsService(VehicleWheelService vehicleWheelService) {
		this.vehicleWheelService = vehicleWheelService;
	}

	public List<VehiclePart> getAndNotify() {
		return vehicleWheelScraperEnabled ? vehicleWheelService.getAndNotify() : Collections.emptyList();
	}

}
