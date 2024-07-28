package com.proman.domainmanager.controller;

import com.proman.domainmanager.model.Domain;
import com.proman.domainmanager.model.Response;
import com.proman.domainmanager.model.Telegram;
import com.proman.domainmanager.service.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/telegram")
public class TelegramController {
    private  TelegramService telegramService;

    @Autowired
    public TelegramController(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @GetMapping
    public ResponseEntity<Response<Telegram>> getAllTelegram(){
        List<Telegram> telegrams = telegramService.findAll();
        Response<Telegram> response = new Response<>(HttpStatus.OK.value(), telegrams);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response<Telegram>> createTelegram(@RequestBody Telegram telegram){
        Telegram telenew = telegramService.createTelegram(telegram);
        List<Telegram> telegrams = new ArrayList<>();
        telegrams.add(telenew);
        Response<Telegram> res = new Response<>(HttpStatus.OK.value(), telegrams);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Telegram>> updateTelegram(@PathVariable Long id ,@RequestBody Telegram telegram){
        Telegram telegramold = telegramService.updateTelegram(id, telegram);
        List<Telegram> telegrams = new ArrayList<>();
        telegrams.add(telegramold);
        Response<Telegram> res = new Response<>(HttpStatus.OK.value(), telegrams);
        return ResponseEntity.ok(res);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response<Telegram>> getTelegram(@PathVariable Long id){
        Telegram telegram = telegramService.findById(id);
        List<Telegram> telegrams = new ArrayList<>();
        telegrams.add(telegram);
        Response<Telegram> res = new Response<>(HttpStatus.OK.value(), telegrams);
        return ResponseEntity.ok(res);
    }
    @DeleteMapping("/{id}")
    public void deleteTelegram(@PathVariable Long id){
        telegramService.deleteTelegram(id);
    }
}

