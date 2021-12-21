package lv.gusevs.rsser.messaging.telegram;

class TelegramMessageSendingFailed extends RuntimeException {

    TelegramMessageSendingFailed(Exception exception) {
        super("Failed sending message to Telegram", exception);
    }

}
