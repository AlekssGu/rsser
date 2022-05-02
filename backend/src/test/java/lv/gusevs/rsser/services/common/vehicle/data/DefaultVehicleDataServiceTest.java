package lv.gusevs.rsser.services.common.vehicle.data;

import com.google.common.eventbus.EventBus;
import lv.gusevs.rsser.services.common.vehicle.event.NewVehicleNotification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
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
public class DefaultVehicleDataServiceTest {

    private static final Sort SORT_BY_ID_DESCENDING = Sort.by(Sort.Order.desc("id"));
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

    @Mock
    private VehicleRepository vehicleRepository;
    @Mock
    private EventBus eventBus;

    @Captor
    private ArgumentCaptor<Example<VehicleData>> vehicleExampleDataCaptor;
    @Captor
    private ArgumentCaptor<VehicleData> vehicleDataCaptor;
    @Captor
    private ArgumentCaptor<NewVehicleNotification> notificationCaptor;

    @InjectMocks
    private DefaultVehicleDataService dataService;

    @Test
    public void returnsEmptyVehicleList() {
        List<Vehicle> vehicles = dataService.getLatest();

        assertThat(vehicles).isEmpty();
    }

    @Test
    public void retrievesLatestVehicles() {
        given(vehicleRepository.findAll(SORT_BY_ID_DESCENDING)).willReturn(List.of(vehicleData()));

        List<Vehicle> vehicles = dataService.getLatest();

        assertThat(vehicles).hasSize(1)
                .first()
                .returns(LINK, Vehicle::getLink)
                .returns(DESCRIPTION, Vehicle::getDescription)
                .returns(MAKE, Vehicle::getMake)
                .returns(MODEL, Vehicle::getModel)
                .returns(PRICE, Vehicle::getPrice)
                .returns(DATE_PUBLISHED, Vehicle::getDatePublished)
                .returns(MILEAGE, Vehicle::getMileage)
                .returns(MAKE_YEAR, Vehicle::getMakeYear)
                .returns(MOTOR_CAPACITY, Vehicle::getMotorCapacity)
                .returns(IMAGE_URL, Vehicle::getImageUrl);
    }

    @Test
    public void vehicleIsSavedToDatabaseAndNotificationIsSent() {
        Vehicle vehicle = vehicle();

        dataService.save(vehicle);

        InOrder order = Mockito.inOrder(vehicleRepository, eventBus);
        then(vehicleRepository).should(order).save(vehicleDataCaptor.capture());
        then(eventBus).should(order).post(notificationCaptor.capture());
        assertThat(vehicleDataCaptor.getValue())
                .returns(vehicle.getLink(), VehicleData::getLink)
                .returns(vehicle.getDescription(), VehicleData::getDescription)
                .returns(vehicle.getMake(), VehicleData::getMake)
                .returns(vehicle.getModel(), VehicleData::getModel)
                .returns(vehicle.getPrice(), VehicleData::getPrice)
                .returns(vehicle.getDatePublished(), VehicleData::getDatePublished)
                .returns(vehicle.getMileage(), VehicleData::getMileage)
                .returns(vehicle.getMakeYear(), VehicleData::getMakeYear)
                .returns(vehicle.getMotorCapacity(), VehicleData::getMotorCapacity)
                .returns(vehicle.getImageUrl(), VehicleData::getImageUrl);
        assertThat(notificationCaptor.getValue().getVehicle()).isEqualTo(vehicle);
    }

    @Test
    public void returnsTrueWhenVehicleIsNew() {
        given(vehicleRepository.exists(any())).willReturn(false);

        boolean isNewVehicle = dataService.isNew(vehicle());

        assertThat(isNewVehicle).isTrue();
        then(vehicleRepository).should().exists(vehicleExampleDataCaptor.capture());
        assertThat(vehicleExampleDataCaptor.getValue().getProbe())
                .returns(LINK, VehicleData::getLink)
                .returns(DESCRIPTION, VehicleData::getDescription)
                .returns(MAKE, VehicleData::getMake)
                .returns(MODEL, VehicleData::getModel)
                .returns(PRICE, VehicleData::getPrice)
                .returns(DATE_PUBLISHED, VehicleData::getDatePublished)
                .returns(MILEAGE, VehicleData::getMileage)
                .returns(MAKE_YEAR, VehicleData::getMakeYear)
                .returns(MOTOR_CAPACITY, VehicleData::getMotorCapacity)
                .returns(IMAGE_URL, VehicleData::getImageUrl);
    }

    @Test
    public void returnsFalseWhenVehicleAlreadyExists() {
        given(vehicleRepository.exists(any())).willReturn(true);
        boolean isNewVehicle = dataService.isNew(vehicle());

        assertThat(isNewVehicle).isFalse();
        then(vehicleRepository).should().exists(vehicleExampleDataCaptor.capture());
        assertThat(vehicleExampleDataCaptor.getValue().getProbe())
                .returns(LINK, VehicleData::getLink)
                .returns(DESCRIPTION, VehicleData::getDescription)
                .returns(MAKE, VehicleData::getMake)
                .returns(MODEL, VehicleData::getModel)
                .returns(PRICE, VehicleData::getPrice)
                .returns(DATE_PUBLISHED, VehicleData::getDatePublished)
                .returns(MILEAGE, VehicleData::getMileage)
                .returns(MAKE_YEAR, VehicleData::getMakeYear)
                .returns(MOTOR_CAPACITY, VehicleData::getMotorCapacity)
                .returns(IMAGE_URL, VehicleData::getImageUrl);
    }

    private VehicleData vehicleData() {
        return VehicleData.builder()
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