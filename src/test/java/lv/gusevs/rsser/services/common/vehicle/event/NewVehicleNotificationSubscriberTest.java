package lv.gusevs.rsser.services.common.vehicle.event;

import com.google.common.eventbus.EventBus;
import lv.gusevs.rsser.event.subscribers.Notification;
import lv.gusevs.rsser.services.common.vehicle.data.Vehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class NewVehicleNotificationSubscriberTest {

    @Mock
    private EventBus eventBus;
    @Mock
    private VehicleNotificationBuilder notificationBuilder;
    @Mock
    private NewVehicleNotification newVehicleNotification;
    @Mock
    private Notification notification;
    @Mock
    private Vehicle vehicle;

    @InjectMocks
    private NewVehicleNotificationSubscriber subscriber;

    @Captor
    private ArgumentCaptor<Object> objectArgumentCaptor;

    @Test
    public void registersSubscriber() {
        subscriber.init();

        then(eventBus).should().register(objectArgumentCaptor.capture());
        assertThat(objectArgumentCaptor.getValue()).isInstanceOf(NewVehicleNotificationSubscriber.class);
    }

    @Test
    public void sendsMessageUponReceivingNotification() {
        given(newVehicleNotification.getVehicle()).willReturn(vehicle);
        given(notificationBuilder.notificationOf(vehicle)).willReturn(notification);

        subscriber.sendMessage(newVehicleNotification);

        InOrder order = Mockito.inOrder(notificationBuilder, eventBus);
        then(notificationBuilder).should(order).notificationOf(vehicle);
        then(eventBus).should(order).post(notification);
    }

}