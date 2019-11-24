package lv.gusevs.rsser.notifiers.telegram;

import lv.gusevs.rsser.notifiers.Notification;

public interface ItemNotifier {

    void newNotification(Notification notification);

}
