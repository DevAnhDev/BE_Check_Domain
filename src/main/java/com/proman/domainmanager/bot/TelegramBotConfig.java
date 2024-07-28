package com.proman.domainmanager.bot;

import com.proman.domainmanager.service.SSEService;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfig {
    private final String botToken = "7310852666:AAEERLmUwQp9iwjOsB6JorE4tLPALf_IKTQ";
    private final String botUsername = "CheckDomainS66Bot";
    @Autowired
    private SSEService sseService;

    @Bean
    public YourTelegramBot yourTelegramBot() {
        DefaultBotOptions options = new DefaultBotOptions();
        return new YourTelegramBot(options, botToken, botUsername, sseService);
    }

    @Bean
    public TelegramBotsApi telegramBotsApi(YourTelegramBot yourTelegramBot) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(yourTelegramBot);
        return telegramBotsApi;
    }

}

