package lv.gusevs.rsser.services.ss.vehicle.wheels;

import lv.gusevs.rsser.services.ss.vehicle.wheels.data.VehicleWheelDataScraper;
import lv.gusevs.rsser.services.ss.vehicle.wheels.data.VehicleWheelDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;

@Singleton
@Service
public class VehicleWheelService {

	private final VehicleWheelDataService vehicleWheelDataService;
	private final VehicleWheelDataScraper vehicleWheelDataScraper;

	@Value("#{new Boolean('${system.vehicle_wheel_scraper_enabled}')}")
	private boolean vehicleWheelScraperEnabled;

	@Autowired
	VehicleWheelService(VehicleWheelDataService vehicleWheelDataService,
						VehicleWheelDataScraper vehicleWheelDataScraper) {
		this.vehicleWheelDataService = vehicleWheelDataService;
		this.vehicleWheelDataScraper = vehicleWheelDataScraper;
	}

	public List<VehicleWheel> newAds() {
		vehicleWheelDataScraper.scrapeNewAds(); // TODO Run in the background
		return vehicleWheelScraperEnabled ? vehicleWheelDataService.getLatest() : Collections.emptyList();
	}

}
