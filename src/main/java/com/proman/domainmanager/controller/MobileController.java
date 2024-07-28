package com.proman.domainmanager.controller;
import com.proman.domainmanager.model.Mobile;
import com.proman.domainmanager.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/mobile")
public class MobileController {
    @Autowired
    private MobileService mobileService;



    @PutMapping("/{id}/{check}")
    public ResponseEntity<Mobile> updateViettel(@PathVariable Long id,@PathVariable Boolean check, @RequestBody Mobile mobile) {
        System.out.println("data en"+check);
        if (!check) {
            Mobile updateMobile = mobileService.updateMobile(id, mobile);
            return ResponseEntity.ok(updateMobile);

        }{
            Mobile updateMobile = mobileService.updateMobileTrue(id, mobile);
            return ResponseEntity.ok(updateMobile);


        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Mobile> findAllViettelsById(@PathVariable Long id) {
        Mobile moble = mobileService.getByIdMoble(id);
        return ResponseEntity.ok(moble);
    }
}
