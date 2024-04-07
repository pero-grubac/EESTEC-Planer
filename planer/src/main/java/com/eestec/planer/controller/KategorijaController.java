package com.eestec.planer.controller;

import com.eestec.planer.dto.KategorijaDTO;
import com.eestec.planer.service.implementations.KategorijaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
public class KategorijaController {
    private final KategorijaServiceImpl kategorijaService;
    private final Logger logger = LoggerFactory.getLogger(KategorijaController.class);

    @Autowired
    public KategorijaController(KategorijaServiceImpl kategorijaService) {
        this.kategorijaService = kategorijaService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<KategorijaDTO>> getAllKategorije() {
        List<KategorijaDTO> kategorije = kategorijaService.getAllKategorije();
        return ResponseEntity.ok(kategorije);
    }
/*  @PostMapping("/createCategory")
  public ResponseEntity<KategorijaDTO> createCategory(@RequestBody KategorijaDTO kategorijaDTO) {
      KategorijaDTO kategorija = kategorijaService.createCategory(kategorijaDTO);
      if (kategorija != null)
          return ResponseEntity.ok(kategorija);
      return ResponseEntity.notFound().build();
  }*/

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    public ResponseEntity<?> createCategory(@RequestBody KategorijaDTO kategorija) {

        kategorijaService.createCategory(kategorija);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    public ResponseEntity<Void> deleteKategorija(@PathVariable Integer id) {
        boolean isOk = kategorijaService.deleteKategorija(id);
        if (isOk) return ResponseEntity.ok().build();
        else return ResponseEntity.notFound().build();
    }


    @PutMapping("/update")
    public ResponseEntity<KategorijaDTO> updateCategory(@RequestBody KategorijaDTO updatedCategory) {
        KategorijaDTO category = kategorijaService.updateCategory(updatedCategory);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/get/{idKategorije}")
    public ResponseEntity<KategorijaDTO> getKategorija(@PathVariable int idKategorije) {
        KategorijaDTO kategorija = kategorijaService.getKategoriju(idKategorije);
        if (kategorija != null) {
            return ResponseEntity.ok(kategorija);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getByIdTim/{idTim}")
    @PreAuthorize("hasAuthority('KORISNIK') || hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    public ResponseEntity<List<KategorijaDTO>> getAllByIdTim(@PathVariable Integer idTim) {
       // logger.info("sta je " + idTim.toString());
        List<KategorijaDTO> kategorije = kategorijaService.getAllByIdTim(idTim);
        return ResponseEntity.ok(kategorije);
    }

}
