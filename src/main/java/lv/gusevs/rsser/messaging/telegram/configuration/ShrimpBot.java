package lv.gusevs.rsser.messaging.telegram.configuration;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ShrimpBot extends TelegramLongPollingBot {

    private static final String TELEGRAM_BOT_NAME = "MrShrimpBot";

    @Override
    public String getBotUsername() {
        return TELEGRAM_BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return System.getenv().get("TELEGRAM_BOT_TOKEN");
    }

    @Override
    public void onUpdateReceived(Update update) {

    }
}
