package lv.gusevs.rsser.services.ss.vehicle.wheels.event;

import lombok.Builder;
import lombok.Getter;
import lv.gusevs.rsser.services.ss.vehicle.wheels.VehicleWheel;

@Builder
@Getter
public class NewVehicleWheelNotification {

    private VehicleWheel vehicleWheel;

}