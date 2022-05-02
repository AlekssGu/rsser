package lv.gusevs.rsser.utilities;

public class TextHelper {

    public static String nvl(String value, String valueIfEmpty) {
        String notNullString = value;

        if (isEmpty(notNullString)) {
            notNullString = valueIfEmpty;
        }

        return notNullString;
    }

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    public static boolean isEmpty(String value) {
        return value == null || value.length() == 0;
    }
}
