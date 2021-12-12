package lv.gusevs.rsser.services.ss.vehicle.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
class VehicleData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String link;
    private String description;
    private String make;
    private String model;
    private String price;
    private String datePublished;
    private String mileage;
    private String makeYear;
    private String motorCapacity;
    private String imageUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleData that = (VehicleData) o;
        return id.equals(that.id) && link.equals(that.link) && description.equals(that.description) && make.equals(that.make) && model.equals(that.model) && price.equals(that.price) && Objects.equals(datePublished, that.datePublished) && Objects.equals(mileage, that.mileage) && Objects.equals(makeYear, that.makeYear) && Objects.equals(motorCapacity, that.motorCapacity) && Objects.equals(imageUrl, that.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, link, description, make, model, price, datePublished, mileage, makeYear, motorCapacity, imageUrl);
    }
}
