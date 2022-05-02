package lv.gusevs.rsser.services.common.vehicle.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class VehicleMapperTest {

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

    @Test
    public void mapsToVehicleData() {
        Vehicle vehicle = vehicle();

        VehicleData vehicleData = VehicleMapper.mapToData(vehicle);

        assertThat(vehicleData)
                .returns(vehicleData.getLink(), VehicleData::getLink)
                .returns(vehicleData.getDescription(), VehicleData::getDescription)
                .returns(vehicleData.getMake(), VehicleData::getMake)
                .returns(vehicleData.getModel(), VehicleData::getModel)
                .returns(vehicleData.getPrice(), VehicleData::getPrice)
                .returns(vehicleData.getDatePublished(), VehicleData::getDatePublished)
                .returns(vehicleData.getMileage(), VehicleData::getMileage)
                .returns(vehicleData.getMakeYear(), VehicleData::getMakeYear)
                .returns(vehicleData.getMotorCapacity(), VehicleData::getMotorCapacity)
                .returns(vehicleData.getImageUrl(), VehicleData::getImageUrl);
    }

    @Test
    public void mapsToVehicle() {
        VehicleData vehicleData = vehicleData();

        Vehicle vehicle = VehicleMapper.toVehicle(vehicleData);

        assertThat(vehicle)
                .returns(vehicleData.getLink(), Vehicle::getLink)
                .returns(vehicleData.getDescription(), Vehicle::getDescription)
                .returns(vehicleData.getMake(), Vehicle::getMake)
                .returns(vehicleData.getModel(), Vehicle::getModel)
                .returns(vehicleData.getPrice(), Vehicle::getPrice)
                .returns(vehicleData.getDatePublished(), Vehicle::getDatePublished)
                .returns(vehicleData.getMileage(), Vehicle::getMileage)
                .returns(vehicleData.getMakeYear(), Vehicle::getMakeYear)
                .returns(vehicleData.getMotorCapacity(), Vehicle::getMotorCapacity)
                .returns(vehicleData.getImageUrl(), Vehicle::getImageUrl);
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