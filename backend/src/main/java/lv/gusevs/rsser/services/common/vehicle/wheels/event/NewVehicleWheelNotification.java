package lv.gusevs.rsser.services.common.vehicle.wheels.event;

import lombok.Builder;
import lombok.Getter;
import lv.gusevs.rsser.services.common.vehicle.wheels.data.VehicleWheel;

@Builder
@Getter
public class NewVehicleWheelNotification {

    private VehicleWheel vehicleWheel;

}