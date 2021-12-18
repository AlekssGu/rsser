package lv.gusevs.rsser.services.ss.vehicle.wheels.job;

import lv.gusevs.rsser.services.ss.vehicle.wheels.data.VehicleWheelDataScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class VehicleWheelBackgroundTask {

    private final VehicleWheelDataScraper vehicleWheelDataScraper;

    @Autowired
    VehicleWheelBackgroundTask(VehicleWheelDataScraper vehicleWheelDataScraper) {
        this.vehicleWheelDataScraper = vehicleWheelDataScraper;
    }

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        vehicleWheelDataScraper.scrapeNewAds();
    }

}
