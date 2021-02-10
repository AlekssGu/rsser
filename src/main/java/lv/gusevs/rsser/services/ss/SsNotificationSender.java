package lv.gusevs.rsser.services.ss;

import static lv.gusevs.rsser.utilities.TextHelper.nvl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lv.gusevs.rsser.notifiers.ImmutableNotification;
import lv.gusevs.rsser.notifiers.Notification;
import lv.gusevs.rsser.notifiers.telegram.TelegramNotifier;
import lv.gusevs.rsser.services.ss.vehicle.Vehicle;

@Component
public class SsNotificationSender {

    private TelegramNotifier telegramNotifier;

    @Autowired
    public SsNotificationSender(TelegramNotifier telegramNotifier) {
        this.telegramNotifier = telegramNotifier;
    }

    public void sendNotification(Notification notification) {
    	telegramNotifier.newNotification(notification);
	}

    public void sendNotification(Vehicle vehicle) {
        telegramNotifier.newNotification(buildNotification(vehicle));
    }

    private Notification buildNotification(Vehicle vehicle) {
        return ImmutableNotification.builder()
                .subject("Jauns BMW!")
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
		return nvl(vehicle.getMake(), "BMW") + " " + vehicle.getModel() + " " + vehicle.getMotorCapacity();
	}
}
