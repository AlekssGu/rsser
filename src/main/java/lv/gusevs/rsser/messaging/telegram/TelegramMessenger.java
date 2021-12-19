package lv.gusevs.rsser.messaging.telegram;

import lv.gusevs.rsser.messaging.telegram.configuration.ShrimpBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramMessenger {

    private static final String CHAT_ID = System.getenv().get("TELEGRAM_CHAT_ID");

    private final ShrimpBot shrimpBot;

    @Autowired
    TelegramMessenger(ShrimpBot shrimpBot) {
        this.shrimpBot = shrimpBot;
    }

    public void sendMessage(String message) {
        try {
            shrimpBot.execute(sendActionOf(message));
        } catch (TelegramApiException exception) {
            throw new RuntimeException("Failed sending message to Telegram", exception);
        }
    }

    private SendMessage sendActionOf(String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(CHAT_ID);
        sendMessage.setText(message);
        return sendMessage;
    }

}
