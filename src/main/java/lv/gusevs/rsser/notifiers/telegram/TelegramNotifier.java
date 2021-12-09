package lv.gusevs.rsser.notifiers.telegram;

import lv.gusevs.rsser.notifiers.Notification;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
class TelegramNotifier implements ItemNotifier {

    private static final int CHAT_ID = Integer.parseInt(System.getenv().get("TELEGRAM_CHAT_ID"));
    private static final String TELEGRAM_API_URL = System.getenv().get("TELEGRAM_API_URL");

	@Override
	public void newNotification(Notification notification) {
		sendMessage(messageTextOf(notification));
	}

	private String messageTextOf(Notification notification) {
		return notification.message() + "\n" + notification.action();
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

			os.write(json.getBytes(StandardCharsets.UTF_8));
			os.close();

			InputStream in = new BufferedInputStream(conn.getInputStream());
			String result = org.apache.commons.io.IOUtils.toString(in, StandardCharsets.UTF_8);

			in.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
