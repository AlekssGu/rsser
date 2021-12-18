package lv.gusevs.rsser.services.ss.vehicle.wheels;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class VehicleWheel {

	private Date datePublished;
	private String link;
	private String price;
	private String description;
	private String imageUrl;

}
