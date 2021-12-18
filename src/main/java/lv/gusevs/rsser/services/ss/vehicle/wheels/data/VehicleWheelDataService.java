package lv.gusevs.rsser.services.ss.vehicle.wheels.data;

import lv.gusevs.rsser.services.ss.vehicle.wheels.VehicleWheel;

import java.util.List;

public interface VehicleWheelDataService {

    List<VehicleWheel> getLatest();

    void save(VehicleWheel vehicleWheel);

    boolean isNew(VehicleWheel vehicleWheel);

}
