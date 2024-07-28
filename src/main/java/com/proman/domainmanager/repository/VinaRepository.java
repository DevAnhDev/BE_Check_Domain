package com.proman.domainmanager.repository;

import com.proman.domainmanager.model.Vina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VinaRepository extends JpaRepository<Vina, Long> {
}
