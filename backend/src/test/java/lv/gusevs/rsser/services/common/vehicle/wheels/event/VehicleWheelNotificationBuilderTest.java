package lv.gusevs.rsser.services.common.vehicle.wheels.event;

import lv.gusevs.rsser.event.subscribers.Notification;
import lv.gusevs.rsser.services.common.vehicle.wheels.data.VehicleWheel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class VehicleWheelNotificationBuilderTest {

    private static final String LINK = "link";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";
    private static final Date DATE_PUBLISHED = new Date();
    private static final String IMAGE_URL = "image url";
    private static final String NOTIFICATION_SUBJECT = "New wheel ad!";

    @InjectMocks
    private VehicleWheelNotificationBuilder notificationBuilder;

    @Test
    public void buildsNotificationBasedOnWheelData(){
        Notification notification = notificationBuilder.notificationOf(vehicleWheel());

        assertThat(notification.getSubject()).isEqualTo(NOTIFICATION_SUBJECT);
        assertThat(notification.getMessage()).contains(PRICE);
        assertThat(notification.getMessage()).contains(DESCRIPTION);
        assertThat(notification.getMessage()).contains(IMAGE_URL);
        assertThat(notification.getAction()).contains(LINK);
        assertThat(notification.getImageUrl()).isEqualTo(IMAGE_URL);
    }

    private VehicleWheel vehicleWheel() {
        return VehicleWheel.builder()
                .datePublished(DATE_PUBLISHED)
                .link(LINK)
                .price(PRICE)
                .description(DESCRIPTION)
                .imageUrl(IMAGE_URL)
                .build();
    }

}