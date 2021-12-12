package lv.gusevs.rsser.services.ss.vehicle.data;

import java.util.List;

public interface VehicleDataService {

    List<Vehicle> getLatest();

    void save(Vehicle vehicle);

    boolean isNew(Vehicle vehicle);

}
