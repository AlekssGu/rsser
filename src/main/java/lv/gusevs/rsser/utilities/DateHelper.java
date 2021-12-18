package lv.gusevs.rsser.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateHelper {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

    public static Date of(String rawDate) {
        Date date = null;
        try {
            date = DATE_FORMAT.parse(rawDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
