package lv.gusevs.rsser.services.ss;

import lv.gusevs.rsser.services.ss.vehicle.VehicleService;
import lv.gusevs.rsser.services.ss.vehicle.data.Vehicle;
import lv.gusevs.rsser.services.ss.vehicle.wheels.VehicleWheel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ss")
class SsServiceController {

	private final VehicleService vehicleService;

	@Autowired
	SsServiceController(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	@GetMapping("/cars")
	public List<Vehicle> getLatestVehicles() {
		return vehicleService.newAds();
	}

	@GetMapping("/car-wheels")
	public List<VehicleWheel> getLatestVehicleWheels() {
		return vehicleService.newWheelAds();
	}

}
