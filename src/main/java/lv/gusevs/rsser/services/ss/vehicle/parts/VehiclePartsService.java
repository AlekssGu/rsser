package lv.gusevs.rsser.services.ss.vehicle.parts;

import java.util.Collections;
import java.util.List;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.gusevs.rsser.configuration.SystemSwitch;
import lv.gusevs.rsser.services.ss.vehicle.parts.wheels.VehicleWheelService;

@Singleton
@Service
public class VehiclePartsService {

	private final VehicleWheelService vehicleWheelService;
	private final SystemSwitch systemSwitch;

	@Autowired
	VehiclePartsService(VehicleWheelService vehicleWheelService,
			SystemSwitch systemSwitch) {
		this.vehicleWheelService = vehicleWheelService;
		this.systemSwitch = systemSwitch;
	}

	public List<VehiclePart> getAndNotify() {
		return systemSwitch.vehicleWheelScraperEnabled() ? vehicleWheelService.getAndNotify() : Collections.emptyList();
	}

}
