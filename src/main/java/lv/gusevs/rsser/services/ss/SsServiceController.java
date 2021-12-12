package lv.gusevs.rsser.services.ss;

import lv.gusevs.rsser.services.ss.vehicle.VehicleService;
import lv.gusevs.rsser.services.ss.vehicle.data.Vehicle;
import lv.gusevs.rsser.services.ss.vehicle.parts.VehiclePart;
import lv.gusevs.rsser.services.ss.vehicle.parts.VehiclePartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ss")
class SsServiceController {

	private final VehicleService vehicleService;
	private final VehiclePartsService vehiclePartsService;

	@Autowired
	SsServiceController(VehicleService vehicleService,
			VehiclePartsService vehiclePartsService) {
		this.vehicleService = vehicleService;
		this.vehiclePartsService = vehiclePartsService;
	}

	@GetMapping("/cars")
	public List<Vehicle> scrapeVehicles() {
		return vehicleService.newAds();
	}

	@GetMapping("/car-wheels")
	public List<VehiclePart> scrapeVehicleWheels() {
		return vehiclePartsService.getAndNotify();
	}

}
