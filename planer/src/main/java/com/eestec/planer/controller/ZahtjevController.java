package com.eestec.planer.controller;


import com.eestec.planer.dto.ZahtjevDTO;
import com.eestec.planer.service.ZahtjevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/question")
@CrossOrigin(origins = "http://localhost:3000")
public class ZahtjevController {
    @Autowired
    ZahtjevService zahtjevService;
    private final Logger logger = LoggerFactory.getLogger(ZahtjevController.class);
    @GetMapping("/all")
    public List<ZahtjevDTO> getAllQuestions() {

        return zahtjevService.getAllZahtjevi();

    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> getDeleteZahtjev(@PathVariable int id) {
        boolean result = zahtjevService.deleteZahtjev(id);
        if (result)
            return ResponseEntity.ok("Zahtjev s ID-om " + id + " je obrisan.");
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/find/{id}")
    public ZahtjevDTO getZahtjevById(@PathVariable int id) {
        return zahtjevService.getZahtjevById(id);
    }


    @PostMapping("/add")
    public ResponseEntity<?> addZahtjev(@RequestBody ZahtjevDTO zahtjev) {
        logger.info(" " + zahtjev.toString());
        ZahtjevDTO zahtjevDTO= zahtjevService.addZahtjev(zahtjev);
        if(zahtjevDTO!=null)
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<String> approveRequest(@PathVariable int id) {
        boolean result = zahtjevService.odobriZahtjev(id);
        if (result) return ResponseEntity.ok("Zahtjev s ID-om " + id + " je odobren.");
        else return ResponseEntity.notFound().build();
    }


}