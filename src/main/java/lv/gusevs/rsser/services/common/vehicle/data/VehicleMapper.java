package lv.gusevs.rsser.services.common.vehicle.data;

class VehicleMapper {

    static VehicleData mapToData(Vehicle vehicle) {
        VehicleData vehicleData = new VehicleData();
        vehicleData.setLink(vehicle.getLink());
        vehicleData.setDescription(vehicle.getDescription());
        vehicleData.setMake(vehicle.getMake());
        vehicleData.setModel(vehicle.getModel());
        vehicleData.setPrice(vehicle.getPrice());
        vehicleData.setDatePublished(vehicle.getDatePublished());
        vehicleData.setMileage(vehicle.getMileage());
        vehicleData.setMakeYear(vehicle.getMakeYear());
        vehicleData.setMotorCapacity(vehicle.getMotorCapacity());
        vehicleData.setImageUrl(vehicle.getImageUrl());
        return vehicleData;
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
