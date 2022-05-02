package lv.gusevs.rsser.services.common.vehicle.wheels.event;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lv.gusevs.rsser.event.subscribers.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
class NewVehicleWheelNotificationSubscriber {

    private final EventBus eventBus;
    private final VehicleWheelNotificationBuilder notificationBuilder;

    @Autowired
    NewVehicleWheelNotificationSubscriber(EventBus eventBus,
                                          VehicleWheelNotificationBuilder notificationBuilder) {
        this.eventBus = eventBus;
        this.notificationBuilder = notificationBuilder;
    }

    @PostConstruct
    void init() {
        eventBus.register(this);
    }

    @Subscribe
    void sendMessage(NewVehicleWheelNotification vehicleWheelNotification) {
        Notification notification = notificationBuilder.notificationOf(vehicleWheelNotification.getVehicleWheel());
        eventBus.post(notification);
    }

}
