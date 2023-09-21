package com.eestec.planer.controller;


import com.eestec.planer.controller.util.KorisnikZadatak;
import com.eestec.planer.dao.KategorijaDAO;
import com.eestec.planer.dto.KategorijaDTO;
import com.eestec.planer.dto.ZadatakDTO;
import com.eestec.planer.dto.ZahtjevDTO;
import com.eestec.planer.service.ZadatakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/zadatak")
@CrossOrigin(origins = "http://localhost:3000")
public class ZadatakController {
    @Autowired
    ZadatakService zadatakService;
    @Autowired
    private KategorijaDAO kategorijaDAO;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('KORISNIK')")
    public List<ZadatakDTO> getAllZadaci() {

        return zadatakService.getAllZadaci();

    }


    //  @PreAuthorize("hasAuthority('ROLE_SUPERUSER')")
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    public ResponseEntity<?> kreirajZadatak(@RequestBody ZadatakDTO zadatakDTO) {
        ZadatakDTO kreiraniZadatak = zadatakService.createZadatak(zadatakDTO);

        if (kreiraniZadatak != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(kreiraniZadatak);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Došlo je do greške prilikom kreiranja zadatka.");
        }
    }

    @GetMapping("/find/{id}")
    public ZadatakDTO getZadatakById(@PathVariable int id) {
        return zadatakService.getZadatak(id);
    }

    @GetMapping("/byCategory/{idKategorije}")
    public ResponseEntity<List<ZadatakDTO>> getZadaciByKategorijaId(@PathVariable int idKategorije) {
        List<ZadatakDTO> zadaci = zadatakService.getZadaciByKategorijaId(idKategorije);
        return ResponseEntity.ok(zadaci);
    }

    @GetMapping("/byTim/{idTim}")
    @PreAuthorize("hasAuthority('KORISNIK') || hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    public ResponseEntity<Map<String, List<ZadatakDTO>>> getZadaciByTimId(@PathVariable int idTim) {
        Map<String, List<ZadatakDTO>> groupedZadaci = new HashMap<>();
        List<KategorijaDTO> kategorije = kategorijaDAO.findByTimDTO_IdTim(idTim);

        for (KategorijaDTO kategorija : kategorije) {
            String nazivKategorije = kategorija.getNaziv();
            List<ZadatakDTO> zadaci = zadatakService.getZadaciByKategorijaId(kategorija.getIdKategorija());

            if (groupedZadaci.containsKey(nazivKategorije)) {
                groupedZadaci.get(nazivKategorije).addAll(zadaci);
            } else {
                groupedZadaci.put(nazivKategorije, new ArrayList<>(zadaci));
            }
        }

        return ResponseEntity.ok(groupedZadaci);
    }

}