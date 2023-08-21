package com.eestec.planer.controller;

import com.eestec.planer.dto.AdminDTO;
import com.eestec.planer.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
    private final AdminServiceImpl adminService; // Use AdminServiceImpl

    @Autowired
    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @GetMapping()
    public String hello (){
        return "hello";
    }

    @GetMapping("/getall")
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        List<AdminDTO> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

//    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public ResponseEntity<AdminDTO> getAdminById(@PathVariable int id) {
//        AdminDTO admin = adminService.getAdminById(id);
//        if (admin != null) {
//            return ResponseEntity.ok(admin);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


    @PostMapping("/new")
    public String createAdmin(@RequestBody AdminDTO adminDTO) {
        String message = adminService.createAdmin(adminDTO);
        return message;
    }

//    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable int id, @RequestBody AdminDTO adminDTO) {
//        AdminDTO updatedAdmin = adminService.updateAdmin(id, adminDTO);
//        if (updatedAdmin != null) {
//            return ResponseEntity.ok(updatedAdmin);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public ResponseEntity<Void> deleteAdmin(@PathVariable int id) {
//        adminService.deleteAdmin(id);
//        return ResponseEntity.noContent().build();
//    }
}
