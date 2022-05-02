package lv.gusevs.rsser.services.ss.vehicle.job;

import lv.gusevs.rsser.services.ss.vehicle.data.VehicleDataScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class VehicleBackgroundTask {

    private static final long ONE_HOUR_IN_MILLIS = 3600000;

    private final VehicleDataScraper vehicleDataScraper;

    @Autowired
    VehicleBackgroundTask(VehicleDataScraper vehicleDataScraper) {
        this.vehicleDataScraper = vehicleDataScraper;
    }

    @Scheduled(fixedRate = ONE_HOUR_IN_MILLIS)
    public void scrapeNewAds() {
        vehicleDataScraper.scrapeNewAds();
    }

}
