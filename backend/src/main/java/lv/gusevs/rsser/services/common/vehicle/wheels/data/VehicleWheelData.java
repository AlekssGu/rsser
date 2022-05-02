package lv.gusevs.rsser.services.common.vehicle.wheels.data;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
class VehicleWheelData {

    @Id
    private String id;
    private Date datePublished;
    private String link;
    private String price;
    private String description;
    private String imageUrl;

}
