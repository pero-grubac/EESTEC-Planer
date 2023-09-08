package com.eestec.planer.controller;

import com.eestec.planer.dto.ClanOdboraDTO;
import com.eestec.planer.service.ClanOdboraServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clanodbora")
@CrossOrigin(origins = "http://localhost:3000")
public class ClanOdboraController {
    private final ClanOdboraServiceImpl clanOdboraService;

    @Autowired
    public ClanOdboraController(ClanOdboraServiceImpl clanOdboraService){
        this.clanOdboraService=clanOdboraService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ClanOdboraDTO>> getAllClanOdbora(){
        List<ClanOdboraDTO> clanOdboraDTOList= clanOdboraService.getAllClanOdbora();
        return ResponseEntity.ok(clanOdboraDTOList);

    }

    @PostMapping("/new")
    public ResponseEntity<ClanOdboraDTO> createClanOdbora(@RequestParam Integer id){
        ClanOdboraDTO clanOdboraDTO = clanOdboraService.createClanOdbora(id);
        if(clanOdboraDTO!=null)
            return ResponseEntity.ok(clanOdboraDTO);
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ClanOdboraDTO> deleteClanOdbora(@PathVariable Integer id){
        boolean isOK= clanOdboraService.deleteClanOdbora(id);
        if(isOK) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
