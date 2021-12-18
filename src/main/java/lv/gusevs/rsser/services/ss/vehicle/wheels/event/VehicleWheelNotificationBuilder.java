package lv.gusevs.rsser.services.ss.vehicle.wheels.event;

import lv.gusevs.rsser.event.subscribers.Notification;
import lv.gusevs.rsser.services.ss.vehicle.wheels.VehicleWheel;
import org.springframework.stereotype.Component;

@Component
class VehicleWheelNotificationBuilder {

    Notification notificationOf(VehicleWheel vehicleWheel) {
        return buildNotification(vehicleWheel);
    }

    private Notification buildNotification(VehicleWheel vehicleWheel) {
        return Notification.builder()
                .subject("Jauni diski!")
                .message(messageOf(vehicleWheel))
                .action("\nApskatīt: " + vehicleWheel.getLink())
                .imageUrl(vehicleWheel.getImageUrl())
                .build();
    }

    private String messageOf(VehicleWheel vehicleWheel) {
        return "Cena: " + vehicleWheel.getPrice() + "\n" +
                vehicleWheel.getDescription() + "\n" +
                vehicleWheel.getImageUrl();
    }
}
