package lv.gusevs.rsser.services.common.vehicle;

import lv.gusevs.rsser.services.common.vehicle.data.Vehicle;
import lv.gusevs.rsser.services.common.vehicle.data.VehicleDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {

    @Mock
    private VehicleDataService dataService;
    @Mock
    private Vehicle vehicle;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    public void returnsEmptyListWhenNoDataFound() {
        List<Vehicle> vehicles = vehicleService.newAds();

        assertThat(vehicles).isEmpty();
    }

    @Test
    public void providesLatestAds() {
        given(dataService.getLatest()).willReturn(List.of(vehicle, vehicle));

        List<Vehicle> vehicles = vehicleService.newAds();

        assertThat(vehicles).containsExactly(vehicle, vehicle);
    }

}