package com.eestec.P.eestecP.Controller;

import com.eestec.P.eestecP.Service.ZahtjevService;
import com.eestec.P.eestecP.Zahtjev.Zahtjev;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class Controller {
    @Autowired
    ZahtjevService zahtjevService;

    @GetMapping("/all")
    public List<Zahtjev> getAllQuestions() {

        return zahtjevService.getAllZahtjevi();

    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> getDeleteZahtjev(@PathVariable int id) {

        zahtjevService.deleteZahtjev(id);
        return ResponseEntity.ok("Zahtjev s ID-om " + id + " je obrisan.");
    }

    @GetMapping("/find/{id}")
    public Zahtjev getZahtjevById(@PathVariable int id)
    {
        return zahtjevService.getZahtjevById(id);
    }


    @PostMapping("/add")
    public Zahtjev addZahtjev(@RequestBody Zahtjev zahtjev)
    {
        return zahtjevService.addZahtjev(zahtjev);
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<String> approveRequest(@PathVariable int id) {
        zahtjevService.odobriZahtjev(id);
        return ResponseEntity.ok("Zahtjev s ID-om " + id + " je odobren.");
    }


}