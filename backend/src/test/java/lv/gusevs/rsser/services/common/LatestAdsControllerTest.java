package lv.gusevs.rsser.services.common;

import lv.gusevs.rsser.services.common.vehicle.VehicleService;
import lv.gusevs.rsser.services.common.vehicle.data.Vehicle;
import lv.gusevs.rsser.services.common.vehicle.wheels.VehicleWheelService;
import lv.gusevs.rsser.services.common.vehicle.wheels.data.VehicleWheel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class LatestAdsControllerTest {

    @Mock
    private VehicleService vehicleService;
    @Mock
    private VehicleWheelService vehicleWheelService;
    @Mock
    private Vehicle vehicle;
    @Mock
    private VehicleWheel vehicleWheel;

    @InjectMocks
    private LatestAdsController adsController;

    @Test
    public void providesLatestVehicles() {
        given(vehicleService.newAds()).willReturn(List.of(vehicle, vehicle));

        List<Vehicle> latestVehicles = adsController.getLatestVehicles();

        assertThat(latestVehicles).containsExactly(vehicle, vehicle);
    }

    @Test
    public void providesEmptyListOfLatestVehicles() {
        List<Vehicle> latestVehicles = adsController.getLatestVehicles();

        assertThat(latestVehicles).isEmpty();
    }

    @Test
    public void providesLatestVehicleWheels() {
        given(vehicleWheelService.newAds()).willReturn(List.of(vehicleWheel, vehicleWheel));

        List<VehicleWheel> latestVehicleWheels = adsController.getLatestVehicleWheels();

        assertThat(latestVehicleWheels).containsExactly(vehicleWheel, vehicleWheel);
    }

    @Test
    public void providesEmptyListOfVehicleWheels() {
        List<VehicleWheel> latestVehicleWheels = adsController.getLatestVehicleWheels();

        assertThat(latestVehicleWheels).isEmpty();
    }

}