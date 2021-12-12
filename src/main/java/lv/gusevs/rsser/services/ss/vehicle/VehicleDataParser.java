package lv.gusevs.rsser.services.ss.vehicle;

import lv.gusevs.rsser.services.ss.vehicle.data.Vehicle;
import org.dom4j.Node;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static lv.gusevs.rsser.utilities.TextHelper.nvl;

@Component
public class VehicleDataParser {

    Vehicle getVehicle(Node node) {
        String description = node.selectSingleNode("description").getText();
        String imageUrl = getPart(description, "src\\s*=\\s*\"(.+?)\"");
        String cleanDescription = description.replaceAll("\\<.*?\\>", " ");
        cleanDescription = cleanDescription.replaceAll("(Apskatīt sludinājumu|€)", "");
        cleanDescription = cleanDescription.trim().replaceAll(" +", " ");

        return Vehicle.builder()
                .link(node.selectSingleNode("link").getText())
                .description(description)
                .imageUrl(imageUrl)
                .datePublished(node.selectSingleNode("pubDate").getText())
                .make(getPart(cleanDescription, "Marka: ([^\\s]+)"))
                .model(getPart(cleanDescription, "Modelis: (.+) Gads:"))
                .makeYear(getPart(cleanDescription, "Gads: (\\d{4})"))
                .price(getPart(cleanDescription, "Cena: (\\d*\\,?\\d*)"))
                .motorCapacity(getPart(cleanDescription, "Tilp.: (\\d\\.\\d(\\w)*)"))
                .mileage(nvl(getPart(cleanDescription, "(\\d*\\stūkst.)"), "Nav norādīts"))
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
