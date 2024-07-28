package com.proman.domainmanager.controller;

import com.proman.domainmanager.model.Domain;
import com.proman.domainmanager.model.Viettel;
import com.proman.domainmanager.repository.ViettelRepository;
import com.proman.domainmanager.service.ViettelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viettel")
public class ViettelController {
    @Autowired
    private ViettelService viettelService;


    @PutMapping("/{id}/{check}")
    public ResponseEntity<Viettel> updateViettel(@PathVariable Long id,@PathVariable boolean check, @RequestBody Viettel viettel) {
        System.out.print("datacheck: "+ check);
        if (!check) {
            Viettel updateViettel = viettelService.updateViettel(id, viettel);
            return ResponseEntity.ok(updateViettel);
        }else{
            Viettel updateViettel = viettelService.updateViettelTrue(id, viettel);
            return ResponseEntity.ok(updateViettel);


        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Viettel> findAllViettelsById(@PathVariable Long id) {
        Viettel viettel = viettelService.getByIdViettel(id);
        return ResponseEntity.ok(viettel);
    }
}
