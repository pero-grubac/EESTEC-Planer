package com.example.EestecPlaner.EestecPlaner.Controller;

import com.example.EestecPlaner.EestecPlaner.Service.QuestionService;
import com.example.EestecPlaner.EestecPlaner.Zahtjev.Zahtjev;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class Controller {
    @Autowired
    QuestionService questionService;

    @GetMapping("/all")
    public List<Zahtjev> getAllQuestions() {

        return questionService.getAllZahtjevi();

    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> getDeleteZahtjev(@PathVariable int id) {

        questionService.deleteZahtjev(id);
        return ResponseEntity.ok("Zahtjev s ID-om " + id + " je obrisan.");
    }

    @GetMapping("/find/{id}")
    public Zahtjev getZahtjevById(@PathVariable int id)
    {
        return questionService.getZahtjevbyId(id);
    }
    @PostMapping("/add")
    public String addZahtjev(@RequestBody Zahtjev zahtjev)
    {
        return questionService.addZahtjev(zahtjev);

    }
    @PostMapping("/approve/{id}")
    public ResponseEntity<String> approveRequest(@PathVariable int id) {
        questionService.odobriZahtjev(id);
        return ResponseEntity.ok("Zahtjev s ID-om " + id + " je odobren.");
    }


}