package org.example.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Service
public class TelegramService extends TelegramLongPollingBot {

    @Value("${telegram.bot.username}")
    private String botUsername;
    @Value("${telegram.bot.token}")
    private String botToken;


    @Override
    public String getBotUsername() {
        // Return the username of your bot
        return botUsername;
    }

    @Override
    public String getBotToken() {
        // Return the token of your bot
        return botToken;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }


    @Override
    public void onUpdateReceived(Update update) {

        // Check if the message contains text
        if (update.hasMessage()) {
            var message = update.getMessage();
            if (message.hasText()) {
                String text = message.getText();
                var chatId = message.getChatId();
                // Process the received message
                String response = "Received message: " + text;
                if (text.equals("/start")) {
                    response = String.format("Hi %s and yeah! Bot was started!", update.getMessage().getChat().getUserName());
                }

                // Send the response back to the user
                SendMessage sendMessage = new SendMessage(String.valueOf(chatId), response);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
}
