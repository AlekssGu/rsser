package lv.gusevs.rsser.services.ss.vehicle.parts.wheels;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Singleton;

import org.dom4j.Node;
import org.springframework.stereotype.Component;

import lv.gusevs.rsser.services.ss.vehicle.parts.VehiclePart;

@Singleton
@Component
class VehicleWheelDataParser {

	VehiclePart parse(Node node) {
		return vehiclePartOf(node);
	}

	private VehiclePart vehiclePartOf(Node node) {
		String description = node.selectSingleNode("description").getText();
		String imageUrl = getPart(description, "src\\s*=\\s*\"(.+?)\"");
		description = description.replaceAll("\\<.*?\\>", " ");
		description = description.replaceAll("(Apskatīt sludinājumu|€)", "");
		description = description.trim().replaceAll(" +", " ");

		return VehiclePart.builder()
				.datePublished(node.selectSingleNode("pubDate").getText())
				.link(node.selectSingleNode("link").getText())
				.price(getPart(description, "Cena: (\\d*\\,?\\d*)"))
				.description(node.selectSingleNode("title").getText())
				.imageUrl(imageUrl)
				.build();
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
