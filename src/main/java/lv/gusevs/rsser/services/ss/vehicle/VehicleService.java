package lv.gusevs.rsser.services.ss.vehicle;

import lv.gusevs.rsser.services.ss.vehicle.data.Vehicle;
import lv.gusevs.rsser.services.ss.vehicle.data.VehicleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import java.util.List;

@Singleton
@Service
public class VehicleService {

    private final VehicleDataService vehicleDataService;

    @Autowired
    VehicleService(VehicleDataService vehicleDataService) {
        this.vehicleDataService = vehicleDataService;
    }

    public List<Vehicle> newAds() {
        return vehicleDataService.getLatest();
    }

}
