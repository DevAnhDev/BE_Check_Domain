package com.proman.domainmanager.service;

import com.proman.domainmanager.bot.YourTelegramBot;
import com.proman.domainmanager.exception.DomainNotFoundException;
import com.proman.domainmanager.model.Mobile;
import com.proman.domainmanager.model.Telegram;
import com.proman.domainmanager.repository.MobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MobileService {
    @Autowired
    private MobileRepository mobileRepository;
    @Autowired
    private YourTelegramBot yourTelegramBot;
    @Autowired
    private TelegramService telegramService;


    public Mobile getByIdMoble(Long id) {
        return mobileRepository.findById(id).orElseThrow(()->new DomainNotFoundException("Domain Notfound id: "+id));
    }
    public Mobile updateMobile(Long id, Mobile mobile) {
        try {
            Mobile oldViet = getByIdMoble(id);
            if (mobile.getActive() == false) {
                String contentMessage =
                        "Domain: " + mobile.getDomainName() +
                                "\nIpAddress: " + mobile.getIpAddress() +
                                "\nNetWork: " + mobile.getNetWork() +
                                "\nNot Active!!!";
                try {
                    //get list telegram
                   List<Telegram> listTelegram =  telegramService.findAll();
                    if (listTelegram.size() > 0) {
                        for (Telegram telegram : listTelegram) {
                            yourTelegramBot.sendMessage(telegram.getChatId(), contentMessage);
//                       System.out.print("Telegram :"+telegram.getChatId());
                        }
                    }
//                    yourTelegramBot.sendMessage("5665173242", contentMessage);
                } catch (Exception e) {
                    System.out.println("Error sending message: " + e.getMessage());
                    // Có thể log hoặc xử lý ngoại lệ tùy ý
                }
            }
            oldViet.setActive(mobile.getActive());
            oldViet.setDescription(mobile.getDescription());
            return mobileRepository.save(oldViet);
        } catch (Exception e) {
            System.out.println("Error updating mobile: " + e.getMessage());
            // Có thể log hoặc xử lý ngoại lệ tùy ý
            return null; // hoặc ném ngoại lệ tiếp tục nếu cần
        }
    }

    public Mobile updateMobileTrue(Long id, Mobile mobile) {
        try {
            Mobile oldViet = getByIdMoble(id);
            oldViet.setActive(mobile.getActive());
            oldViet.setDescription(mobile.getDescription());
            return mobileRepository.save(oldViet);
        } catch (Exception e) {
            System.out.println("Error updating mobile: " + e.getMessage());
            // Có thể log hoặc xử lý ngoại lệ tùy ý
            return null; // hoặc ném ngoại lệ tiếp tục nếu cần
        }
    }


}
