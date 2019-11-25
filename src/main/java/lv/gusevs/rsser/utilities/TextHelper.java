package lv.gusevs.rsser.utilities;

public class TextHelper {

    public static String nvl(String value, String valueIfEmpty) {
        String notNullString = value;

        if (notNullString == null || notNullString.length() == 0) {
            notNullString = valueIfEmpty;
        }

        return notNullString;
    }
}
