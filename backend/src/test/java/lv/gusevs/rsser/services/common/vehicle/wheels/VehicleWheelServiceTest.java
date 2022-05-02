package lv.gusevs.rsser.services.common.vehicle.wheels;

import lv.gusevs.rsser.services.common.vehicle.wheels.data.VehicleWheel;
import lv.gusevs.rsser.services.common.vehicle.wheels.data.VehicleWheelDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class VehicleWheelServiceTest {

    @Mock
    private VehicleWheelDataService dataService;
    @Mock
    private VehicleWheel vehicleWheel;

    @InjectMocks
    private VehicleWheelService vehicleWheelService;

    @Test
    public void returnsEmptyListWhenNoDataFound() {
        List<VehicleWheel> vehicleWheels = vehicleWheelService.newAds();

        assertThat(vehicleWheels).isEmpty();
    }

    @Test
    public void providesLatestAds() {
        given(dataService.getLatest()).willReturn(List.of(vehicleWheel, vehicleWheel));

        List<VehicleWheel> vehicleWheels = vehicleWheelService.newAds();

        assertThat(vehicleWheels).containsExactly(vehicleWheel, vehicleWheel);
    }

}