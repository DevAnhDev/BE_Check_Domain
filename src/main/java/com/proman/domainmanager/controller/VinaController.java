package com.proman.domainmanager.controller;
import com.proman.domainmanager.model.Vina;
import com.proman.domainmanager.service.VinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vina")
public class VinaController {
    @Autowired
    private VinaService vinaService;

    @PutMapping("/{id}/{check}")
    public ResponseEntity<Vina> updateVina(@PathVariable Long id,@PathVariable Boolean check, @RequestBody Vina vina) {
       if(!check){
           Vina updateVina = vinaService.updateVina(id, vina);
           return ResponseEntity.ok(updateVina);
       }else{
           Vina updateVina = vinaService.updateVinaTrue(id, vina);
           return ResponseEntity.ok(updateVina);
       }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Vina> findAllVinasById(@PathVariable Long id) {
        Vina vina = vinaService.getByIdVina(id);
        return ResponseEntity.ok(vina);
    }
}
