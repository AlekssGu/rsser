package lv.gusevs.rsser.services.common.vehicle.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
@Builder
@ToString
class VehicleData {

    @Id
    private final String id;
    private final String link;
    private final String description;
    private final String make;
    private final String model;
    private final String price;
    private final Date datePublished;
    private final String mileage;
    private final String makeYear;
    private final String motorCapacity;
    private final String imageUrl;

}
