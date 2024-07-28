package com.proman.domainmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LongPollingController {

    @GetMapping("/api/long-polling")
    public String longPolling() throws InterruptedException {

        Thread.sleep(5000);
        // Trả về dữ liệu hoặc thông báo
        return "Data from server after long polling";
    }
}