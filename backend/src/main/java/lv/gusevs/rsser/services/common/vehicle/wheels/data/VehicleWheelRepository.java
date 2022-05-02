package lv.gusevs.rsser.services.common.vehicle.wheels.data;

import org.springframework.data.mongodb.repository.MongoRepository;

interface VehicleWheelRepository extends MongoRepository<VehicleWheelData, String> {
}
