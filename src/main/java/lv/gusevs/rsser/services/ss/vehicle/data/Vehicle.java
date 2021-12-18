package lv.gusevs.rsser.services.ss.vehicle.data;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class Vehicle {

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
