package lv.gusevs.rsser.services.ss.vehicle.wheels.data;

import com.google.common.eventbus.EventBus;
import lv.gusevs.rsser.services.ss.vehicle.wheels.VehicleWheel;
import lv.gusevs.rsser.services.ss.vehicle.wheels.event.NewVehicleWheelNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class DefaultWheelDataService implements VehicleWheelDataService {

    private final VehicleWheelRepository vehicleRepository;
    private final EventBus eventBus;

    @Autowired
    DefaultWheelDataService(VehicleWheelRepository vehicleRepository,
                            EventBus eventBus) {
        this.vehicleRepository = vehicleRepository;
        this.eventBus = eventBus;
    }

    @Override
    public List<VehicleWheel> getLatest() {
        return vehicleRepository.findAll(Sort.by(Sort.Order.desc("id")))
                .stream()
                .map(VehicleWheelMapper::toVehicleWheel)
                .collect(Collectors.toList());
    }

    @Override
    public void save(VehicleWheel vehicleWheel) {
        VehicleWheelData vehicleWheelData = VehicleWheelMapper.mapToData(vehicleWheel);
        vehicleRepository.save(vehicleWheelData);
        postNotificationAbout(vehicleWheel);
    }

    @Override
    public boolean isNew(VehicleWheel vehicleWheel) {
        VehicleWheelData vehicleWheelData = VehicleWheelMapper.mapToData(vehicleWheel);
        return !vehicleRepository.exists(Example.of(vehicleWheelData));
    }

    private void postNotificationAbout(VehicleWheel vehicleWheel) {
        eventBus.post(newVehicleNotification(vehicleWheel));
    }

    private NewVehicleWheelNotification newVehicleNotification(VehicleWheel vehicleWheel) {
        return NewVehicleWheelNotification.builder()
                .vehicleWheel(vehicleWheel)
                .build();
    }

}
