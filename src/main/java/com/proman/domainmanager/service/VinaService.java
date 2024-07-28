package com.proman.domainmanager.service;

import com.proman.domainmanager.bot.YourTelegramBot;
import com.proman.domainmanager.exception.DomainNotFoundException;
import com.proman.domainmanager.model.Domain;
import com.proman.domainmanager.model.Telegram;
import com.proman.domainmanager.model.Viettel;
import com.proman.domainmanager.model.Vina;
import com.proman.domainmanager.repository.ViettelRepository;
import com.proman.domainmanager.repository.VinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VinaService {
    @Autowired
    private VinaRepository vinaRepository;
    @Autowired
    private YourTelegramBot yourTelegramBot;
    @Autowired
    private TelegramService telegramService;


    public Vina getByIdVina(Long id) {
        return vinaRepository.findById(id).orElseThrow(()->new DomainNotFoundException("Domain Notfound id: "+id));
    }

    public Vina updateVina(Long id, Vina vina) {
        try {
            Vina oldVina = getByIdVina(id);
            if (vina.getActive() == false) {
                String contentMessage =
                        "Domain: " + vina.getDomainName() +
                                "\nIpAddress: " + vina.getIpAddress() +
                                "\nNetWork: " + vina.getNetWork() +
                                "\nNot Active!!!";
                try {
                    //get list telegram
                    List<Telegram> listTelegram = telegramService.findAll();
                    if (listTelegram.size() > 0) {
                        for (Telegram telegram : listTelegram) {
                            yourTelegramBot.sendMessage(telegram.getChatId(), contentMessage);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error sending message: " + e.getMessage());
                    // Có thể log hoặc xử lý ngoại lệ tùy ý
                }
            }
            oldVina.setActive(vina.getActive());
            oldVina.setDescription(vina.getDescription());
            return vinaRepository.save(oldVina);
        } catch (Exception e) {
            System.out.println("Error updating mobile: " + e.getMessage());
            // Có thể log hoặc xử lý ngoại lệ tùy ý
            return null;
        }
    }

    public Vina updateVinaTrue(Long id, Vina vina) {
        try {
            Vina oldVina = getByIdVina(id);
            oldVina.setActive(vina.getActive());
            oldVina.setDescription(vina.getDescription());
            return vinaRepository.save(oldVina);
        } catch (Exception e) {
            System.out.println("Error updating mobile: " + e.getMessage());
            // Có thể log hoặc xử lý ngoại lệ tùy ý
            return null;
        }
    }

}
