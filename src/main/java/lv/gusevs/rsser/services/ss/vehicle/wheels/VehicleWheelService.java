package lv.gusevs.rsser.services.ss.vehicle.wheels;

import lv.gusevs.rsser.services.ss.vehicle.wheels.data.VehicleWheelDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import java.util.List;

@Singleton
@Service
public class VehicleWheelService {

	private final VehicleWheelDataService vehicleWheelDataService;

	@Autowired
	VehicleWheelService(VehicleWheelDataService vehicleWheelDataService) {
		this.vehicleWheelDataService = vehicleWheelDataService;
	}

	public List<VehicleWheel> newAds() {
		return vehicleWheelDataService.getLatest();
	}

}
