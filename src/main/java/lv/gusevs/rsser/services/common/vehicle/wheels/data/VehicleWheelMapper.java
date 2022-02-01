package lv.gusevs.rsser.services.common.vehicle.wheels.data;

class VehicleWheelMapper {

    static VehicleWheelData mapToData(VehicleWheel vehicleWheel) {
        return VehicleWheelData.builder()
                .datePublished(vehicleWheel.getDatePublished())
                .link(vehicleWheel.getLink())
                .price(vehicleWheel.getPrice())
                .description(vehicleWheel.getDescription())
                .imageUrl(vehicleWheel.getImageUrl())
                .build();
    }

    static VehicleWheel toVehicleWheel(VehicleWheelData vehicleWheelData) {
        return VehicleWheel.builder()
                .datePublished(vehicleWheelData.getDatePublished())
                .link(vehicleWheelData.getLink())
                .price(vehicleWheelData.getPrice())
                .description(vehicleWheelData.getDescription())
                .imageUrl(vehicleWheelData.getImageUrl())
                .build();
    }

}
