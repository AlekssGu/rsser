package lv.gusevs.rsser.event.subscribers;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lv.gusevs.rsser.messaging.telegram.TelegramMessenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NotificationSubscriber {

	private final EventBus eventBus;
	private final TelegramMessenger telegramMessenger;

	@Autowired
	NotificationSubscriber(EventBus eventBus,
						   TelegramMessenger telegramMessenger) {
		this.eventBus = eventBus;
		this.telegramMessenger = telegramMessenger;
	}

	@PostConstruct
	void init() {
		eventBus.register(this);
	}

	@Subscribe
	private void sendMessage(Notification notification) {
		telegramMessenger.sendMessage(messageTextOf(notification));
	}

	private String messageTextOf(Notification notification) {
		return notification.getMessage() + "\n" + notification.getAction();
	}

}
