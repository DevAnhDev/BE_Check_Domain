package com.proman.domainmanager.bot;

import com.proman.domainmanager.service.SSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class YourTelegramBot extends TelegramLongPollingBot {

    private final String botToken;
    private final String botUsername;
    private SSEService sseService;

    public YourTelegramBot(DefaultBotOptions options, String botToken, String botUsername, SSEService sseService) {
        super(options);
        this.botToken = botToken;
        this.botUsername = botUsername;
        this.sseService = sseService;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String messageText = update.getMessage().getText();
            if (messageText.toUpperCase().equals("SCAN")) {
                sseService.notifyClients("true");
                System.out.println(" Message: " + messageText);
            } else {
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText(" Message \"" + messageText+"\" Invalid!!!");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendMessage(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
