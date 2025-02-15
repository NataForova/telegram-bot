package org.example.telegram;

import org.example.telegram.model.TelegramCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

import static org.example.telegram.model.TelegramCommand.*;


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
                var str = START.getCommand();
                if (text != null && !text.trim().isEmpty()) {
                    var chatId = message.getChatId();
                    String response = "Received message: " + text;
                    var command = findCommand(text);
                    switch (command)  {
                        case START:
                            response = String.format("Hi %s and yeah! Bot was started!", update.getMessage().getChat().getUserName());
                            break;
                        case LOGIN:



                    }
                    // Process the received message

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
    }

    private TelegramCommand findCommand(String messageText) {
        if (messageText.equals(START.getCommand())) {
            return START;
        } else if (messageText.equals(LOGIN.getCommand())) {
            return LOGIN;
        } else if (messageText.equals(SIGN_UP.getCommand())) {
            return SIGN_UP;
        }  else if (messageText.equals(ADD_WORD.getCommand())) {
            return ADD_WORD;
        } else if (messageText.equals(FIND_TRANSLATION.getCommand())) {
            return FIND_TRANSLATION;
        } else if (messageText.equals(START_TRAINING.getCommand())) {
            return START_TRAINING;
        } else if (messageText.equals(STOP_TRAINING.getCommand())) {
            return STOP_TRAINING;
        } else if (messageText.equals(GET_RANDOM_WORD.getCommand())) {
            return GET_RANDOM_WORD;
        }
        return null;
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
}
