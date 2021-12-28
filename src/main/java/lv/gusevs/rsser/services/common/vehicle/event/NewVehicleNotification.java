package lv.gusevs.rsser.services.common.vehicle.event;

import lombok.Builder;
import lombok.Getter;
import lv.gusevs.rsser.services.common.vehicle.data.Vehicle;

@Builder
@Getter
public class NewVehicleNotification {

    private Vehicle vehicle;

}
