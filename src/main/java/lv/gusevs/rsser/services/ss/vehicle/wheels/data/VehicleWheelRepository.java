package lv.gusevs.rsser.services.ss.vehicle.wheels.data;

import org.springframework.data.mongodb.repository.MongoRepository;

interface VehicleWheelRepository extends MongoRepository<VehicleWheelData, String> {
}
