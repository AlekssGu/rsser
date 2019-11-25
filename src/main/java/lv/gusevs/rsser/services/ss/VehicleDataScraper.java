package lv.gusevs.rsser.services.ss;

import org.dom4j.Node;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static lv.gusevs.rsser.utilities.TextHelper.nvl;

@Component
public class VehicleDataScraper {

    Vehicle getVehicle(Node node) {
        String description = node.selectSingleNode("description").getText();

        Vehicle vehicle = new Vehicle();
        vehicle.setLink(node.selectSingleNode("link").getText());

        vehicle.setDescription(description);
        vehicle.setImageUrl(getPart(description, "src\\s*=\\s*\"(.+?)\""));
        description = description.replaceAll("\\<.*?\\>", " ");
        description = description.replaceAll("(Apskatīt sludinājumu|€)", "");
        description = description.trim().replaceAll(" +", " ");

//        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault()
        vehicle.setDatePublished(node.selectSingleNode("pubDate").getText());
        vehicle.setMake(getPart(description, "Marka: ([^\\s]+)"));
        vehicle.setModel(getPart(description, "Modelis: (.+) Gads:"));
        vehicle.setMakeYear(getPart(description, "Gads: (\\d{4})"));
        vehicle.setPrice(getPart(description, "Cena: (\\d*\\,?\\d*)"));
        vehicle.setMotorCapacity(getPart(description, "Tilp.: (\\d\\.\\d(\\w)*)"));
        vehicle.setMileage(nvl(getPart(description, "(\\d*\\stūkst.)"), "Nav norādīts"));

        return vehicle;
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