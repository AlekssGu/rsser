package lv.gusevs.rsser.services.common.vehicle.data;

import org.springframework.data.mongodb.repository.MongoRepository;

interface VehicleRepository extends MongoRepository<VehicleData, String> {
}
