package lv.gusevs.rsser.services.ss.vehicle.parts;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class VehiclePart implements Serializable {

	private Date datePublished;
	private String link;
	private String price;
	private String description;
	private String imageUrl;

}
