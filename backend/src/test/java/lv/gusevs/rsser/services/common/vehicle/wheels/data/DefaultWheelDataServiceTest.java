package lv.gusevs.rsser.services.common.vehicle.wheels.data;

import com.google.common.eventbus.EventBus;
import lv.gusevs.rsser.services.common.vehicle.wheels.event.NewVehicleWheelNotification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class DefaultWheelDataServiceTest {

    private static final Sort SORT_BY_ID_DESCENDING = Sort.by(Sort.Order.desc("id"));
    private static final String LINK = "link";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";
    private static final Date DATE_PUBLISHED = new Date();
    private static final String IMAGE_URL = "image url";

    @Mock
    private VehicleWheelRepository wheelRepository;
    @Mock
    private EventBus eventBus;

    @Captor
    private ArgumentCaptor<Example<VehicleWheelData>> vehicleWheelExampleDataCaptor;
    @Captor
    private ArgumentCaptor<VehicleWheelData> vehicleWheelDataCaptor;
    @Captor
    private ArgumentCaptor<NewVehicleWheelNotification> notificationCaptor;

    @InjectMocks
    private DefaultWheelDataService dataService;

    @Test
    public void returnsEmptyVehicleWheelList() {
        List<VehicleWheel> wheels = dataService.getLatest();

        assertThat(wheels).isEmpty();
    }

    @Test
    public void retrievesLatestVehicleWheels() {
        given(wheelRepository.findAll(SORT_BY_ID_DESCENDING)).willReturn(List.of(vehicleWheelData()));

        List<VehicleWheel> wheels = dataService.getLatest();

        assertThat(wheels).hasSize(1)
                .first()
                .returns(LINK, VehicleWheel::getLink)
                .returns(DESCRIPTION, VehicleWheel::getDescription)
                .returns(PRICE, VehicleWheel::getPrice)
                .returns(DATE_PUBLISHED, VehicleWheel::getDatePublished)
                .returns(IMAGE_URL, VehicleWheel::getImageUrl);
    }

    @Test
    public void vehicleWheelIsSavedToDatabaseAndNotificationIsSent() {
        VehicleWheel vehicleWheel = vehicleWheel();

        dataService.save(vehicleWheel);

        InOrder order = Mockito.inOrder(wheelRepository, eventBus);
        then(wheelRepository).should(order).save(vehicleWheelDataCaptor.capture());
        then(eventBus).should(order).post(notificationCaptor.capture());
        assertThat(vehicleWheelDataCaptor.getValue())
                .returns(vehicleWheel.getLink(), VehicleWheelData::getLink)
                .returns(vehicleWheel.getDescription(), VehicleWheelData::getDescription)
                .returns(vehicleWheel.getPrice(), VehicleWheelData::getPrice)
                .returns(vehicleWheel.getDatePublished(), VehicleWheelData::getDatePublished)
                .returns(vehicleWheel.getImageUrl(), VehicleWheelData::getImageUrl);
        assertThat(notificationCaptor.getValue().getVehicleWheel()).isEqualTo(vehicleWheel);
    }

    @Test
    public void returnsTrueWhenVehicleWheelIsNew() {
        given(wheelRepository.exists(any())).willReturn(false);

        boolean isNewVehicleWheel = dataService.isNew(vehicleWheel());

        assertThat(isNewVehicleWheel).isTrue();
        then(wheelRepository).should().exists(vehicleWheelExampleDataCaptor.capture());
        assertThat(vehicleWheelExampleDataCaptor.getValue().getProbe())
                .returns(LINK, VehicleWheelData::getLink)
                .returns(DESCRIPTION, VehicleWheelData::getDescription)
                .returns(PRICE, VehicleWheelData::getPrice)
                .returns(DATE_PUBLISHED, VehicleWheelData::getDatePublished)
                .returns(IMAGE_URL, VehicleWheelData::getImageUrl);
    }

    @Test
    public void returnsFalseWhenVehicleWheelAlreadyExists() {
        given(wheelRepository.exists(any())).willReturn(true);
        boolean isNewVehicle = dataService.isNew(vehicleWheel());

        assertThat(isNewVehicle).isFalse();
        then(wheelRepository).should().exists(vehicleWheelExampleDataCaptor.capture());
        assertThat(vehicleWheelExampleDataCaptor.getValue().getProbe())
                .returns(LINK, VehicleWheelData::getLink)
                .returns(DESCRIPTION, VehicleWheelData::getDescription)
                .returns(PRICE, VehicleWheelData::getPrice)
                .returns(DATE_PUBLISHED, VehicleWheelData::getDatePublished)
                .returns(IMAGE_URL, VehicleWheelData::getImageUrl);
    }

    private VehicleWheelData vehicleWheelData() {
        return VehicleWheelData.builder()
                .datePublished(DATE_PUBLISHED)
                .link(LINK)
                .price(PRICE)
                .description(DESCRIPTION)
                .imageUrl(IMAGE_URL)
                .build();
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