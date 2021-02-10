package lv.gusevs.rsser.notifiers.telegram;

import org.springframework.stereotype.Service;

import lv.gusevs.rsser.notifiers.Notification;

@Service
public interface ItemNotifier {

	void newNotification(Notification notification);

}
