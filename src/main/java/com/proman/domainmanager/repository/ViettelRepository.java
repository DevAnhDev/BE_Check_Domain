package com.proman.domainmanager.repository;

import com.proman.domainmanager.model.Viettel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViettelRepository extends JpaRepository<Viettel, Long> {
}
