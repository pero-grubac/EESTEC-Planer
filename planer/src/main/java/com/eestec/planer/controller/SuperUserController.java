package com.eestec.planer.controller;

import com.eestec.planer.dto.SuperUserDTO;
import com.eestec.planer.service.implementations.SuperUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/superuser")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
public class SuperUserController {

    private final SuperUserServiceImpl superUserService;

    @Autowired
    public SuperUserController(SuperUserServiceImpl superUserService) {
        this.superUserService = superUserService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SuperUserDTO>> getAllSuperUsers() {
        List<SuperUserDTO> superUserDTOList = superUserService.getAllSuperUsers();
        return ResponseEntity.ok(superUserDTOList);
    }

    @PostMapping("/new")
    public ResponseEntity<SuperUserDTO> createSuperUser(@RequestParam Integer id) {
        SuperUserDTO superUserDTO = superUserService.createSuperUser(id);
        if (superUserDTO != null) {
            return ResponseEntity.ok(superUserDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SuperUserDTO> deleteSuperUser(@PathVariable Integer id) {

        boolean isOK = superUserService.deleteSuperUser(id);
        if (isOK) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }


}
