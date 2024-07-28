package com.proman.domainmanager.controller;

import com.proman.domainmanager.model.Domain;
import com.proman.domainmanager.model.Response;
import com.proman.domainmanager.service.DomainService;
import com.proman.domainmanager.service.SSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/domains")
public class DomainController {

    @Autowired
    private DomainService domainService;
    @Autowired
    private SSEService sseService;

    @GetMapping("/scan")
    public void scanDomains(){
        List<Domain> domains = domainService.getAllDomain();
        System.out.println("datasize"+domains.size());
        if (domains.size() > 0) {
            sseService.notifyClients("true");
        }else{
            sseService.notifyClients("false");
        }
    }
    @GetMapping
    public ResponseEntity<Response<Domain>> getAllDomainsWithMobile() {
        List<Domain> domains = domainService.getAllDomain();
        Response<Domain> response = new Response<>(HttpStatus.OK.value(), domains);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<Domain>> createDomain(@RequestBody Domain domain) {
        Domain createdDomain = domainService.createDomain(domain);
        List<Domain> datacreate = new ArrayList<Domain>();
        datacreate.add(domain);
        Response<Domain> response = new Response<>(HttpStatus.OK.value(), datacreate);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Domain>> getDomainById(@PathVariable Long id) {
        Domain domain = domainService.getByIdDomain(id);
        List<Domain> data = new ArrayList<Domain>();
        data.add(domain);
        Response<Domain> response = new Response<>(HttpStatus.OK.value(), data);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Domain>> updateDomain(@PathVariable Long id, @RequestBody Domain domainDetail) {
        Domain updatedDomain = domainService.updateDomain(id, domainDetail);
        List<Domain> data = new ArrayList<>();
        data.add(updatedDomain);
        Response<Domain> response = new Response<>(HttpStatus.OK.value(), data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDomain(@PathVariable Long id) {
        domainService.deleteDomain(id);
        return ResponseEntity.noContent().build();
    }



}
