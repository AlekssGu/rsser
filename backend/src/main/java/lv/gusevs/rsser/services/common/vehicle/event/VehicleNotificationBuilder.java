package lv.gusevs.rsser.services.common.vehicle.event;

import lv.gusevs.rsser.event.subscribers.Notification;
import lv.gusevs.rsser.services.common.vehicle.data.Vehicle;
import org.springframework.stereotype.Component;

import static lv.gusevs.rsser.utilities.TextHelper.nvl;

@Component
class VehicleNotificationBuilder {

    Notification notificationOf(Vehicle vehicle) {
        return buildNotification(vehicle);
    }

    private Notification buildNotification(Vehicle vehicle) {
        return Notification.builder()
                .subject("New ad!")
                .message(prepareMessageText(vehicle))
                .action("\nShow more: " + vehicle.getLink())
                .imageUrl(vehicle.getImageUrl())
                .build();
    }

    private String prepareMessageText(Vehicle vehicle) {
        return "Vehicle: " + vehicleInfo(vehicle) + "\n" +
                "Make year: " + vehicle.getMakeYear() + "\n" +
                "Price: " + vehicle.getPrice() + "\n" +
                vehicle.getImageUrl();
    }

    private String vehicleInfo(Vehicle vehicle) {
        return nvl(vehicle.getMake(), "N/A") + " " + vehicle.getModel() + " " + vehicle.getMotorCapacity();
    }
}
