package lv.gusevs.rsser.services.ss.vehicle.wheels.job;

import lv.gusevs.rsser.services.ss.vehicle.wheels.data.VehicleWheelDataScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class VehicleWheelBackgroundTask {

    private static final long ONE_HOUR_IN_MILLIS = 3600000;

    private final VehicleWheelDataScraper vehicleWheelDataScraper;

    @Autowired
    VehicleWheelBackgroundTask(VehicleWheelDataScraper vehicleWheelDataScraper) {
        this.vehicleWheelDataScraper = vehicleWheelDataScraper;
    }

    @Scheduled(fixedRate = ONE_HOUR_IN_MILLIS)
    public void scrapeNewAds() {
        vehicleWheelDataScraper.scrapeNewAds();
    }

}
