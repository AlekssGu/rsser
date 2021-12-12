package lv.gusevs.rsser.services.ss.vehicle.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class DefaultService implements VehicleDataService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    DefaultService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
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
    }

    @Override
    public boolean isNew(Vehicle vehicle) {
        VehicleData vehicleData = VehicleMapper.mapToData(vehicle);
        return !vehicleRepository.exists(Example.of(vehicleData));
    }

}
