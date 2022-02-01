package lv.gusevs.rsser.services.common.vehicle.wheels.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class VehicleWheelMapperTest {

    private static final String LINK = "link";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";
    private static final Date DATE_PUBLISHED = new Date();
    private static final String IMAGE_URL = "image url";

    @Test
    public void mapsToWheelData() {
        VehicleWheelData vehicleWheelData = VehicleWheelMapper.mapToData(vehicleWheel());

        assertThat(vehicleWheelData)
                .returns(LINK, VehicleWheelData::getLink)
                .returns(DESCRIPTION, VehicleWheelData::getDescription)
                .returns(PRICE, VehicleWheelData::getPrice)
                .returns(DATE_PUBLISHED, VehicleWheelData::getDatePublished)
                .returns(IMAGE_URL, VehicleWheelData::getImageUrl);
    }
    
    @Test
    public void mapsToWheel() {
        VehicleWheel vehicleWheel = VehicleWheelMapper.toVehicleWheel(vehicleWheelData());

        assertThat(vehicleWheel)
                .returns(LINK, VehicleWheel::getLink)
                .returns(DESCRIPTION, VehicleWheel::getDescription)
                .returns(PRICE, VehicleWheel::getPrice)
                .returns(DATE_PUBLISHED, VehicleWheel::getDatePublished)
                .returns(IMAGE_URL, VehicleWheel::getImageUrl);
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