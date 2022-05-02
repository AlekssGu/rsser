package lv.gusevs.rsser.event.subscribers;

import com.google.common.eventbus.EventBus;
import lv.gusevs.rsser.messaging.telegram.TelegramMessenger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class NotificationSubscriberTest {

    private static final String MESSAGE = "some message";
    private static final String ACTION = "some action";
    private static final String NOTIFICATION_MESSAGE = MESSAGE + "\n" + ACTION;

    @Mock
    private EventBus eventBus;
    @Mock
    private TelegramMessenger messenger;
    @Mock
    private Notification notification;

    @InjectMocks
    private NotificationSubscriber subscriber;

    @Captor
    private ArgumentCaptor<Object> objectArgumentCaptor;

    @Test
    public void registersSubscriber() {
        subscriber.init();

        then(eventBus).should().register(objectArgumentCaptor.capture());
        assertThat(objectArgumentCaptor.getValue()).isInstanceOf(NotificationSubscriber.class);
    }

    @Test
    public void sendsMessageUponReceivingNotification() {
        given(notification.getMessage()).willReturn(MESSAGE);
        given(notification.getAction()).willReturn(ACTION);

        subscriber.sendMessage(notification);

        then(messenger).should().sendMessage(NOTIFICATION_MESSAGE);
    }

}