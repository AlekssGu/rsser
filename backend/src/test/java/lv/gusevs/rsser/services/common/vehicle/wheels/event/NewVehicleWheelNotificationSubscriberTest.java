package lv.gusevs.rsser.services.common.vehicle.wheels.event;

import com.google.common.eventbus.EventBus;
import lv.gusevs.rsser.event.subscribers.Notification;
import lv.gusevs.rsser.services.common.vehicle.wheels.data.VehicleWheel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class NewVehicleWheelNotificationSubscriberTest {

    @Mock
    private EventBus eventBus;
    @Mock
    private VehicleWheelNotificationBuilder notificationBuilder;
    @Mock
    private NewVehicleWheelNotification vehicleWheelNotification;
    @Mock
    private Notification notification;
    @Mock
    private VehicleWheel vehicleWheel;

    @InjectMocks
    private NewVehicleWheelNotificationSubscriber subscriber;

    @Captor
    private ArgumentCaptor<Object> objectArgumentCaptor;

    @Test
    public void registersSubscriber() {
        subscriber.init();

        then(eventBus).should().register(objectArgumentCaptor.capture());
        assertThat(objectArgumentCaptor.getValue()).isInstanceOf(NewVehicleWheelNotificationSubscriber.class);
    }

    @Test
    public void sendsMessageUponReceivingNotification() {
        given(vehicleWheelNotification.getVehicleWheel()).willReturn(vehicleWheel);
        given(notificationBuilder.notificationOf(vehicleWheel)).willReturn(notification);

        subscriber.sendMessage(vehicleWheelNotification);

        InOrder order = Mockito.inOrder(notificationBuilder, eventBus);
        then(notificationBuilder).should(order).notificationOf(vehicleWheel);
        then(eventBus).should(order).post(notification);
    }
}