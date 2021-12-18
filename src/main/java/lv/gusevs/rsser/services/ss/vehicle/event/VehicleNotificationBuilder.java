package lv.gusevs.rsser.services.ss.vehicle.event;

import lv.gusevs.rsser.event.subscribers.Notification;
import lv.gusevs.rsser.services.ss.vehicle.data.Vehicle;
import org.springframework.stereotype.Component;

import static lv.gusevs.rsser.utilities.TextHelper.nvl;

@Component
class VehicleNotificationBuilder {

    Notification notificationOf(Vehicle vehicle) {
        return buildNotification(vehicle);
    }

    private Notification buildNotification(Vehicle vehicle) {
        return Notification.builder()
                .subject("Jauns sludinājums!")
                .message(prepareMessageText(vehicle))
                .action("\nApskatīt: " + vehicle.getLink())
                .imageUrl(vehicle.getImageUrl())
                .build();
    }

    private String prepareMessageText(Vehicle vehicle) {
        return "Automašīna: " + vehicleInfo(vehicle) + "\n" +
                "Gads: " + vehicle.getMakeYear() + "\n" +
                "Cena: " + vehicle.getPrice() + "\n" +
                vehicle.getImageUrl();
    }

    private String vehicleInfo(Vehicle vehicle) {
        return nvl(vehicle.getMake(), "N/A") + " " + vehicle.getModel() + " " + vehicle.getMotorCapacity();
    }
}
