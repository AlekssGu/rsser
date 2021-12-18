package lv.gusevs.rsser.services.ss.vehicle.data;

import org.springframework.data.mongodb.repository.MongoRepository;

interface VehicleRepository extends MongoRepository<VehicleData, String> {
}
