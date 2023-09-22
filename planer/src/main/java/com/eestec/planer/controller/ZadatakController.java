package com.eestec.planer.controller;


import com.eestec.planer.controller.util.KategorijeZadaci;
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
    public ResponseEntity<KategorijeZadaci> getZadaciByTimId(@PathVariable int idTim) {
        List<ZadatakDTO> zadaci = new ArrayList<>();
        List<KategorijaDTO> kategorije = kategorijaDAO.findByTimDTO_IdTim(idTim);
        for (KategorijaDTO kategorija : kategorije) {
            List<ZadatakDTO> temp = zadatakService.getZadaciByKategorijaId(kategorija.getIdKategorija());
            zadaci.addAll(temp);
        }
        KategorijeZadaci kategorijeZadaci = new KategorijeZadaci();
        kategorijeZadaci.setKategorije(kategorije);
        kategorijeZadaci.setZadaci(zadaci);
        return ResponseEntity.ok(kategorijeZadaci);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('KORISNIK') || hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    public ResponseEntity<ZadatakDTO> update(@RequestBody ZadatakDTO zadatak) {
        ZadatakDTO zadatakDTO = zadatakService.updateZadatak(zadatak);
        if (zadatakDTO != null)
            return ResponseEntity.ok().build();
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('Koordinator') || hasAuthority('Clan odbora')")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (zadatakService.deleteZadtak(id)) {
            return ResponseEntity.ok("Zahtjev s ID-om " + id + " je obrisan.");
        }
        return ResponseEntity.notFound().build();
    }


}