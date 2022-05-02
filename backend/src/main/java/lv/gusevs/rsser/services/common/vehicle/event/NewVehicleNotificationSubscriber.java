package lv.gusevs.rsser.services.common.vehicle.event;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lv.gusevs.rsser.event.subscribers.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
class NewVehicleNotificationSubscriber {

    private final EventBus eventBus;
    private final VehicleNotificationBuilder notificationBuilder;

    @Autowired
    NewVehicleNotificationSubscriber(EventBus eventBus,
                                     VehicleNotificationBuilder notificationBuilder) {
        this.eventBus = eventBus;
        this.notificationBuilder = notificationBuilder;
    }

    @PostConstruct
    void init() {
        eventBus.register(this);
    }

    @Subscribe
    void sendMessage(NewVehicleNotification vehicleNotification) {
        Notification notification = notificationBuilder.notificationOf(vehicleNotification.getVehicle());
        eventBus.post(notification);
    }

}
