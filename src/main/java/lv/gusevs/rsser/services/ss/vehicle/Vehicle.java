package lv.gusevs.rsser.services.ss.vehicle;

import java.io.Serializable;
import java.util.Objects;

public class Vehicle implements Serializable {
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

    private static final long serialVersionUID = 1239814719;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getMakeYear() {
        return makeYear;
    }

    public void setMakeYear(String makeYear) {
        this.makeYear = makeYear;
    }

    public String getMotorCapacity() {
        return motorCapacity;
    }

    public void setMotorCapacity(String motorCapacity) {
        this.motorCapacity = motorCapacity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return link.equals(vehicle.link) &&
                description.equals(vehicle.description) &&
                make.equals(vehicle.make) &&
                model.equals(vehicle.model) &&
                price.equals(vehicle.price) &&
                datePublished.equals(vehicle.datePublished) &&
                mileage.equals(vehicle.mileage) &&
                makeYear.equals(vehicle.makeYear) &&
                motorCapacity.equals(vehicle.motorCapacity) &&
                imageUrl.equals(vehicle.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, description, make, model, price, datePublished, mileage, makeYear, motorCapacity, imageUrl);
    }
}
