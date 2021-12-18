package lv.gusevs.rsser.services.ss.vehicle.event;

import lombok.Builder;
import lombok.Getter;
import lv.gusevs.rsser.services.ss.vehicle.data.Vehicle;

@Builder
@Getter
public class NewVehicleNotification {

    private Vehicle vehicle;

}
