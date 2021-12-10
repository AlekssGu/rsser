package lv.gusevs.rsser.event.subscribers;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class NotificationSubscriber {

    private static final int CHAT_ID = Integer.parseInt(System.getenv().get("TELEGRAM_CHAT_ID"));
    private static final String TELEGRAM_API_URL = System.getenv().get("TELEGRAM_API_URL");

	private final EventBus eventBus;

	@Autowired
	NotificationSubscriber(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	@PostConstruct
	void init() {
		eventBus.register(this);
	}

	@Subscribe
	private void sendMessage(Notification notification) {
		sendMessage(messageTextOf(notification));
	}

	private String messageTextOf(Notification notification) {
		return notification.getMessage() + "\n" + notification.getAction();
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
