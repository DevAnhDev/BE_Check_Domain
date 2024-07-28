package com.proman.domainmanager.service;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SSEService {

    private final List<SseEmitter> emitters = new ArrayList<>();

    public void addEmitter(SseEmitter emitter) {
        emitters.add(emitter);
        notifyClientsWithConnectionCount(); // Gửi số lượng kết nối mới cho các client
    }

    public void removeEmitter(SseEmitter emitter) {
        emitters.remove(emitter);
        notifyClientsWithConnectionCount(); // Gửi số lượng kết nối còn lại cho các client
    }

    public void notifyClients(String message) {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        emitters.forEach(emitter -> {
            try {
                emitter.send(message, MediaType.TEXT_PLAIN);
            } catch (IOException e) {
                emitter.completeWithError(e);
                deadEmitters.add(emitter);
            }
        });

        // Remove dead emitters
        emitters.removeAll(deadEmitters);
    }

    public int countEmitters() {
        return emitters.size();
    }

    public void notifyClientsWithConnectionCount() {
        String message = "Current connections: " + countEmitters();
        notifyClients(message);
    }

    public void notifyNewData(String newDataMessage) {
        notifyClients("New data: " + newDataMessage);
    }
}
