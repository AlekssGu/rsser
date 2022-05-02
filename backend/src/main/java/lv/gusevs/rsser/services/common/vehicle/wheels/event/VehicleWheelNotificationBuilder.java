package lv.gusevs.rsser.services.common.vehicle.wheels.event;

import lv.gusevs.rsser.event.subscribers.Notification;
import lv.gusevs.rsser.services.common.vehicle.wheels.data.VehicleWheel;
import org.springframework.stereotype.Component;

@Component
class VehicleWheelNotificationBuilder {

    Notification notificationOf(VehicleWheel vehicleWheel) {
        return buildNotification(vehicleWheel);
    }

    private Notification buildNotification(VehicleWheel vehicleWheel) {
        return Notification.builder()
                .subject("New wheel ad!")
                .message(messageOf(vehicleWheel))
                .action("\nShow more: " + vehicleWheel.getLink())
                .imageUrl(vehicleWheel.getImageUrl())
                .build();
    }

    private String messageOf(VehicleWheel vehicleWheel) {
        return "Price: " + vehicleWheel.getPrice() + "\n" +
                vehicleWheel.getDescription() + "\n" +
                vehicleWheel.getImageUrl();
    }
}
