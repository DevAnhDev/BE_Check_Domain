package com.proman.domainmanager.model;

import jakarta.persistence.*;

@Entity
@Table(name="telegram")
public class Telegram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="user_name", length = 256, nullable = false)
    private String userName;

    @Column(name="chat_id", length = 256, nullable = false)
    private String chatId;

    @Column(name="active")
    private Boolean active;



    public Telegram() {
    }

    public Telegram(String userName, String chatId, Boolean active) {
        this.userName = userName;
        this.chatId = chatId;
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
