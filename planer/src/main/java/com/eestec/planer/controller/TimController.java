package com.eestec.planer.controller;


import com.eestec.planer.dto.KorisnikDTO;
import com.eestec.planer.dto.TimDTO;
import com.eestec.planer.service.TimServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
@CrossOrigin(origins = "http://localhost:3000")
public class TimController {

    private final TimServiceImpl timService;

    @Autowired
    public TimController(TimServiceImpl timeService) {
        this.timService = timeService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TimDTO>> getAllTeams() {
        return ResponseEntity.ok(timService.getAllTeams());
    }

    @PostMapping("/new")
    public ResponseEntity<TimDTO> createTim(@RequestBody TimDTO tim) {
        TimDTO timDTO = timService.createTim(tim);
        if (timDTO != null)
            return ResponseEntity.ok(timDTO);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    public ResponseEntity<TimDTO> updateTim(@RequestBody TimDTO timDTO) {
        TimDTO tim = timService.updateTim(timDTO);
        if (tim != null)
            return ResponseEntity.ok(timDTO);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TimDTO> deleteTim(@PathVariable Integer id){
        if(timService.deleteTim(id))
            return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<List<KorisnikDTO>> getAllByIdTim(@PathVariable Integer id){
        List<KorisnikDTO> korisnici= timService.getAllByIdTim(id);
        return  ResponseEntity.ok(korisnici);
    }
}
