package lv.gusevs.rsser.services.common.vehicle.wheels.data;

import java.util.List;

public interface VehicleWheelDataService {

    List<VehicleWheel> getLatest();

    void save(VehicleWheel vehicleWheel);

    boolean isNew(VehicleWheel vehicleWheel);

}
