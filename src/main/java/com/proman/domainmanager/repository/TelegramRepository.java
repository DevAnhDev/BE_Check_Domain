package com.proman.domainmanager.repository;

import com.proman.domainmanager.model.Telegram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramRepository extends JpaRepository<Telegram, Long> {
}
