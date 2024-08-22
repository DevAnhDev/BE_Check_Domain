package com.proman.domainmanager.controller;
import com.proman.domainmanager.service.SSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class SSEController {

    @Autowired
    private SSEService sseService;

    @GetMapping("/api/sse")
    public SseEmitter handleSSE() {
        SseEmitter emitter = new SseEmitter();

        // Thêm emitter vào danh sách
        sseService.addEmitter(emitter);

        // Xử lý khi client đóng kết nối
        emitter.onCompletion(() -> sseService.removeEmitter(emitter));
        emitter.onTimeout(() -> sseService.removeEmitter(emitter));

        return emitter;
    }
}
