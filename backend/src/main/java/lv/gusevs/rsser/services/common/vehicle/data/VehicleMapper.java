package lv.gusevs.rsser.services.common.vehicle.data;

class VehicleMapper {

    static VehicleData mapToData(Vehicle vehicle) {
        return VehicleData.builder()
                .link(vehicle.getLink())
                .description(vehicle.getDescription())
                .make(vehicle.getMake())
                .model(vehicle.getModel())
                .price(vehicle.getPrice())
                .datePublished(vehicle.getDatePublished())
                .mileage(vehicle.getMileage())
                .makeYear(vehicle.getMakeYear())
                .motorCapacity(vehicle.getMotorCapacity())
                .imageUrl(vehicle.getImageUrl())
                .build();
    }

    static Vehicle toVehicle(VehicleData vehicleData) {
        return Vehicle.builder()
                .link(vehicleData.getLink())
                .description(vehicleData.getDescription())
                .make(vehicleData.getMake())
                .model(vehicleData.getModel())
                .price(vehicleData.getPrice())
                .datePublished(vehicleData.getDatePublished())
                .mileage(vehicleData.getMileage())
                .makeYear(vehicleData.getMakeYear())
                .motorCapacity(vehicleData.getMotorCapacity())
                .imageUrl(vehicleData.getImageUrl())
                .build();
    }

}
