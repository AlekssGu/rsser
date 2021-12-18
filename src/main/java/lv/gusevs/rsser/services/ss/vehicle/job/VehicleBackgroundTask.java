package lv.gusevs.rsser.services.ss.vehicle.job;

import lv.gusevs.rsser.services.ss.vehicle.data.VehicleDataScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class VehicleBackgroundTask {

    private final VehicleDataScraper vehicleDataScraper;

    @Autowired
    VehicleBackgroundTask(VehicleDataScraper vehicleDataScraper) {
        this.vehicleDataScraper = vehicleDataScraper;
    }

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        vehicleDataScraper.scrapeNewAds();
    }

}
