package lv.gusevs.rsser.services.ss;

import lv.gusevs.rsser.notifiers.Notification;
import lv.gusevs.rsser.notifiers.NotificationBuilder;
import lv.gusevs.rsser.notifiers.telegram.TelegramNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SsNotificationSender {

    private TelegramNotifier telegramNotifier;

    @Autowired
    public SsNotificationSender(TelegramNotifier telegramNotifier) {
        this.telegramNotifier = telegramNotifier;
    }

    public void sendNotification(Vehicle vehicle) {
        telegramNotifier.newNotification(buildNotification(vehicle));
    }

    private Notification buildNotification(Vehicle vehicle) {
        return new NotificationBuilder
                .Builder("Jauns BMW!")
                .withMessage(prepareMessageText(vehicle))
                .withAction("\nApskatīt: " + vehicle.getLink())
                .build();
    }

    private String prepareMessageText(Vehicle vehicle) {
        return "Automašīna: " + vehicle.getMake() + " " + vehicle.getModel() + "\n" +
                "Gads: " + vehicle.getMakeYear() + "\n" +
                "Cena: " + vehicle.getPrice() + "\n" +
                vehicle.getImageUrl();

    }
}
