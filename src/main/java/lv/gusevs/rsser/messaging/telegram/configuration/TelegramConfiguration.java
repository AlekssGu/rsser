package lv.gusevs.rsser.messaging.telegram.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramConfiguration {

    @Bean
    public TelegramBot telegramBot() {
        ShrimpBot shrimpBot = new ShrimpBot();
        register(shrimpBot);
        return shrimpBot;
    }

    private void register(ShrimpBot shrimpBot) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(shrimpBot);
        } catch (TelegramApiException exception) {
            throw new RuntimeException("Telegram exception happened: ", exception);
        }
    }

}
