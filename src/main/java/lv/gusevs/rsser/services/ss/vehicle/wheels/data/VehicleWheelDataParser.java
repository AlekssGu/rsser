package lv.gusevs.rsser.services.ss.vehicle.wheels.data;

import lv.gusevs.rsser.services.ss.vehicle.wheels.VehicleWheel;
import org.dom4j.Node;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Singleton
@Component
class VehicleWheelDataParser {

	VehicleWheel getVehicleWheel(Node node) {
		return vehiclePartOf(node);
	}

	private VehicleWheel vehiclePartOf(Node node) {
		String description = node.selectSingleNode("description").getText();
		String imageUrl = getPart(description, "src\\s*=\\s*\"(.+?)\"");
		description = description.replaceAll("<.*?>", " ");
		description = description.replaceAll("(Apskatīt sludinājumu|€)", "");
		description = description.trim().replaceAll(" +", " ");

		return VehicleWheel.builder()
				.datePublished(dateOf(node.selectSingleNode("pubDate").getText()))
				.link(node.selectSingleNode("link").getText())
				.price(getPart(description, "Cena: (\\d*\\,?\\d*)"))
				.description(node.selectSingleNode("title").getText().replace("\"", "'"))
				.imageUrl(imageUrl)
				.build();
	}

	private Date dateOf(String date) {
		//new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		//String dateInString = "7-Jun-2013";
		//Date date = formatter.parse(dateInString);
		return new Date(date);
	}

	private String getPart(String description, String regexPattern) {
		String parsedPart = "";
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(description);
		if (matcher.find()) {
			parsedPart = matcher.group(1);
		}
		return parsedPart;
	}

}
