package com.proman.domainmanager.service;

import com.proman.domainmanager.bot.YourTelegramBot;
import com.proman.domainmanager.exception.DomainNotFoundException;
import com.proman.domainmanager.model.Mobile;
import com.proman.domainmanager.model.Telegram;
import com.proman.domainmanager.model.Viettel;
import com.proman.domainmanager.repository.ViettelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViettelService {
    @Autowired
    private ViettelRepository viettelRepository;
    @Autowired
    private YourTelegramBot yourTelegramBot;
    @Autowired
    private TelegramService telegramService;


    public Viettel getByIdViettel(Long id) {
        return viettelRepository.findById(id).orElseThrow(()->new DomainNotFoundException("Domain Notfound id: "+id));
    }

    public Viettel updateViettel(Long id, Viettel viettel) {
            try {
                Viettel oldViet = getByIdViettel(id);
                if (viettel.getActive() == false) {
                    String contentMessage =
                            "Domain: " + viettel.getDomainName() +
                                    "\nIpAddress: " + viettel.getIpAddress() +
                                    "\nNetWork: " + viettel.getNetWork() +
                                    "\nNot Active!!!";

                    try {
                        //get list telegram
                        List<Telegram> listTelegram =  telegramService.findAll();
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
                oldViet.setActive(viettel.getActive());
                oldViet.setDescription(viettel.getDescription());
                return viettelRepository.save(oldViet);

            }catch(Exception e){
                System.out.println("Error updating mobile: " + e.getMessage());
                // Có thể log hoặc xử lý ngoại lệ tùy ý
                return null;
            }
    }


    public Viettel updateViettelTrue(Long id, Viettel viettel) {
        try {
            Viettel oldViet = getByIdViettel(id);
            oldViet.setActive(viettel.getActive());
            oldViet.setDescription(viettel.getDescription());
            return viettelRepository.save(oldViet);
        }catch(Exception e){
            System.out.println("Error updating mobile: " + e.getMessage());
            // Có thể log hoặc xử lý ngoại lệ tùy ý
            return null;
        }
    }

}
