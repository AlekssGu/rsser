package lv.gusevs.rsser.services.common.vehicle.event;

import lv.gusevs.rsser.event.subscribers.Notification;
import lv.gusevs.rsser.services.common.vehicle.data.Vehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class VehicleNotificationBuilderTest {

    private static final String LINK = "link";
    private static final String DESCRIPTION = "description";
    private static final String MAKE = "make";
    private static final String MODEL = "model";
    private static final String PRICE = "price";
    private static final Date DATE_PUBLISHED = new Date();
    private static final String MILEAGE = "mileage";
    private static final String MAKE_YEAR = "make year";
    private static final String MOTOR_CAPACITY = "motor capacity";
    private static final String IMAGE_URL = "image url";
    private static final String NOTIFICATION_SUBJECT = "New ad!";

    @InjectMocks
    private VehicleNotificationBuilder notificationBuilder;

    @Test
    public void buildsNotificationBasedOnVehicleObject() {
        Notification notification = notificationBuilder.notificationOf(vehicle());

        assertThat(notification.getAction()).contains(LINK);
        assertThat(notification.getMessage()).contains(MAKE);
        assertThat(notification.getMessage()).contains(MODEL);
        assertThat(notification.getMessage()).contains(MOTOR_CAPACITY);
        assertThat(notification.getMessage()).contains(MAKE_YEAR);
        assertThat(notification.getMessage()).contains(PRICE);
        assertThat(notification.getMessage()).contains(IMAGE_URL);
        assertThat(notification.getImageUrl()).isEqualTo(IMAGE_URL);
        assertThat(notification.getSubject()).isEqualTo(NOTIFICATION_SUBJECT);
    }

    private Vehicle vehicle() {
        return Vehicle.builder()
                .link(LINK)
                .description(DESCRIPTION)
                .make(MAKE)
                .model(MODEL)
                .price(PRICE)
                .datePublished(DATE_PUBLISHED)
                .mileage(MILEAGE)
                .makeYear(MAKE_YEAR)
                .motorCapacity(MOTOR_CAPACITY)
                .imageUrl(IMAGE_URL)
                .build();
    }

}