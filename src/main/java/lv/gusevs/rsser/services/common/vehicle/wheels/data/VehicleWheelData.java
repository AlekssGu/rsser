package lv.gusevs.rsser.services.common.vehicle.wheels.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
class VehicleWheelData {

    @Id
    private String id;
    private Date datePublished;
    private String link;
    private String price;
    private String description;
    private String imageUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleWheelData that = (VehicleWheelData) o;
        return Objects.equals(id, that.id) && Objects.equals(datePublished, that.datePublished) && Objects.equals(link, that.link) && Objects.equals(price, that.price) && Objects.equals(description, that.description) && Objects.equals(imageUrl, that.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, datePublished, link, price, description, imageUrl);
    }
}
