package com.proman.domainmanager.service;

import com.proman.domainmanager.exception.DomainNotFoundException;
import com.proman.domainmanager.model.Telegram;
import com.proman.domainmanager.repository.TelegramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelegramService {
    private TelegramRepository telegramRepository;

    @Autowired
    public TelegramService(TelegramRepository telegramRepository) {
        this.telegramRepository = telegramRepository;
    }

    public Telegram findById(Long id) {
        return telegramRepository.findById(id).orElseThrow(()->new DomainNotFoundException("Domain Notfound id: "+id));
    }

    public List<Telegram> findAll() {
        return telegramRepository.findAll();
    }

    public Telegram createTelegram(Telegram telegram) {
        return telegramRepository.save(telegram);
    }

    public Telegram updateTelegram(Long id, Telegram telegram) {
        Telegram telegramToUpdate = findById(id);
        telegramToUpdate.setUserName(telegram.getUserName());
        telegramToUpdate.setChatId(telegram.getChatId());
        telegramToUpdate.setActive(telegram.getActive());
        telegramRepository.saveAndFlush(telegramToUpdate);
        return telegramToUpdate;
    }

    public void deleteTelegram(Long id) {
        telegramRepository.deleteById(id);
    }
}
