package lv.gusevs.rsser.services.ss.vehicle;

import lv.gusevs.rsser.services.ss.vehicle.data.Vehicle;
import lv.gusevs.rsser.services.ss.vehicle.data.VehicleDataScraper;
import lv.gusevs.rsser.services.ss.vehicle.data.VehicleDataService;
import lv.gusevs.rsser.services.ss.vehicle.wheels.VehicleWheel;
import lv.gusevs.rsser.services.ss.vehicle.wheels.VehicleWheelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;

@Singleton
@Service
public class VehicleService {

    private final VehicleDataService vehicleDataService;
    private final VehicleDataScraper vehicleDataScraper;
    private final VehicleWheelService vehicleWheelService;

    @Value("#{new Boolean('${system.vehicle_scraper_enabled}')}")
    private boolean vehicleScraperEnabled;

    @Autowired
    VehicleService(VehicleDataService vehicleDataService,
                   VehicleDataScraper vehicleDataScraper,
                   VehicleWheelService vehicleWheelService) {
        this.vehicleDataService = vehicleDataService;
        this.vehicleDataScraper = vehicleDataScraper;
        this.vehicleWheelService = vehicleWheelService;
    }

    public List<Vehicle> newAds() {
        vehicleDataScraper.scrapeNewAds(); // TODO Run this in background, scrape new ads and save to the database
        return vehicleScraperEnabled ? getNewAds() : Collections.emptyList();
    }

    public List<VehicleWheel> newWheelAds() {
        return vehicleWheelService.newAds();
    }

    private List<Vehicle> getNewAds() {
        return vehicleDataService.getLatest();
    }
}
