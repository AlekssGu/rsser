package lv.gusevs.rsser.services.ss.vehicle.wheels.data;

import lv.gusevs.rsser.services.ss.vehicle.wheels.VehicleWheel;

class VehicleWheelMapper {

    static VehicleWheelData mapToData(VehicleWheel vehicleWheel) {
        VehicleWheelData vehicleWheelData = new VehicleWheelData();
        vehicleWheelData.setDatePublished(vehicleWheel.getDatePublished());
        vehicleWheelData.setLink(vehicleWheel.getLink());
        vehicleWheelData.setPrice(vehicleWheel.getPrice());
        vehicleWheelData.setDescription(vehicleWheel.getDescription());
        vehicleWheelData.setImageUrl(vehicleWheel.getImageUrl());
        return vehicleWheelData;
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
