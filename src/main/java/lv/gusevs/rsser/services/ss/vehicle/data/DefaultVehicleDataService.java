package lv.gusevs.rsser.services.ss.vehicle.data;

import com.google.common.eventbus.EventBus;
import lv.gusevs.rsser.services.ss.vehicle.event.NewVehicleNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class DefaultVehicleDataService implements VehicleDataService {

    private final VehicleRepository vehicleRepository;
    private final EventBus eventBus;

    @Autowired
    DefaultVehicleDataService(VehicleRepository vehicleRepository,
                              EventBus eventBus) {
        this.vehicleRepository = vehicleRepository;
        this.eventBus = eventBus;
    }

    @Override
    public List<Vehicle> getLatest() {
        return vehicleRepository.findAll(Sort.by(Sort.Order.desc("id")))
                .stream()
                .map(VehicleMapper::toVehicle)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Vehicle vehicle) {
        VehicleData vehicleData = VehicleMapper.mapToData(vehicle);
        vehicleRepository.save(vehicleData);
        postNotificationAbout(vehicle);
    }

    @Override
    public boolean isNew(Vehicle vehicle) {
        VehicleData vehicleData = VehicleMapper.mapToData(vehicle);
        return !vehicleRepository.exists(Example.of(vehicleData));
    }

    private void postNotificationAbout(Vehicle vehicle) {
        eventBus.post(newVehicleNotification(vehicle));
    }

    private NewVehicleNotification newVehicleNotification(Vehicle vehicle) {
        return NewVehicleNotification.builder()
                .vehicle(vehicle)
                .build();
    }

}
