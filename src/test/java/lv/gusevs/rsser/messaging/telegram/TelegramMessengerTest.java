package lv.gusevs.rsser.messaging.telegram;

import lv.gusevs.rsser.messaging.telegram.configuration.ShrimpBot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TelegramMessengerTest {

    private static final String MESSAGE = "some message";
    private static final String CHAT_ID_PROPERTY_NAME = "TELEGRAM_CHAT_ID";
    private static final String CHAT_ID = "2e3817293fc275dbee74bd71ce6eb056";

    @Mock
    private ShrimpBot shrimpBot;

    @InjectMocks
    private TelegramMessenger messenger;

    @Captor
    private ArgumentCaptor<SendMessage> messageCaptor;

    @Before
    public void setUp() {
        System.setProperty(CHAT_ID_PROPERTY_NAME, CHAT_ID);
    }

    @After
    public void tearDown() {
        System.clearProperty(CHAT_ID_PROPERTY_NAME);
    }

    @Test
    public void sendsMessage() throws TelegramApiException {
        messenger.sendMessage(MESSAGE);

        then(shrimpBot).should().execute(messageCaptor.capture());
        assertThat(messageCaptor.getValue())
                .returns(CHAT_ID, SendMessage::getChatId)
                .returns(MESSAGE, SendMessage::getText);
    }

    @Test
    public void throwsExceptionWhenSendingFailed() throws TelegramApiException {
        willThrow(new TelegramApiException()).given(shrimpBot).execute(any(SendMessage.class));

        assertThatExceptionOfType(TelegramMessageSendingFailed.class)
                .isThrownBy(() -> messenger.sendMessage(MESSAGE));
    }

}