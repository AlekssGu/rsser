package lv.gusevs.rsser.notifiers.telegram;

import lv.gusevs.rsser.notifiers.Notification;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class TelegramNotifier implements ItemNotifier {

    public static final int CHAT_ID = 0;
    public static final String TELEGRAM_API_URL = "https://api.telegram.org/bot{TELEGRAM_API}/sendMessage";

    @Override
    public void newNotification(Notification notification) {
        String text = notification.getMessage() + "\n" +
                notification.getAction();

        sendMessage(text);
    }

    private void sendMessage(String message) {
        try {
            URL url = new URL(TELEGRAM_API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();

            String json = "{ \"chat_id\": \"" + CHAT_ID + "\", \"text\": \"" + message + "\" }";

            os.write(json.getBytes("UTF-8"));
            os.close();

            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");


            in.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
