package lv.gusevs.rsser.services.common;

import lv.gusevs.rsser.services.common.vehicle.VehicleService;
import lv.gusevs.rsser.services.common.vehicle.data.Vehicle;
import lv.gusevs.rsser.services.common.vehicle.wheels.data.VehicleWheel;
import lv.gusevs.rsser.services.common.vehicle.wheels.VehicleWheelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ss")
class LatestAdsController {

	private final VehicleService vehicleService;
	private final VehicleWheelService vehicleWheelService;

	@Autowired
	LatestAdsController(VehicleService vehicleService,
						VehicleWheelService vehicleWheelService) {
		this.vehicleService = vehicleService;
		this.vehicleWheelService = vehicleWheelService;
	}

	@GetMapping("/cars")
	public List<Vehicle> getLatestVehicles() {
		return vehicleService.newAds();
	}

	@GetMapping("/car-wheels")
	public List<VehicleWheel> getLatestVehicleWheels() {
		return vehicleWheelService.newAds();
	}

}
