package com.proman.domainmanager.service;

import com.proman.domainmanager.exception.DomainNotFoundException;
import com.proman.domainmanager.model.Domain;
import com.proman.domainmanager.repository.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainService {
    @Autowired
    private DomainRepository domainRepository;


    public Domain createDomain(Domain domain) {
        return domainRepository.save(domain);
    }
    public List<Domain> getAllDomain() {
        return domainRepository.findAll();
    }
    public Domain getByIdDomain(Long id) {
        return domainRepository.findById(id).orElseThrow(()->new DomainNotFoundException("Domain Notfound id: "+id));
    }


    public Domain updateDomain(Long id, Domain domain) {
        Domain oldDomain = getByIdDomain(id);
        oldDomain.setDomanName(domain.getDomanName());
        oldDomain.setActive(domain.getActive());
        oldDomain.setIpAddress(domain.getIpAddress());
        oldDomain.setDescription(domain.getDescription());
        return domainRepository.save(oldDomain);
    }

    public void deleteDomain(Long id) {
        Domain domain = getByIdDomain(id);
        domainRepository.delete(domain);
    }
}
