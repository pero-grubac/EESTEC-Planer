package com.eestec.planer.controller;

import com.eestec.planer.dto.AdminDTO;
import com.eestec.planer.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
   @GetMapping("/")
        public String hello (){
        return "hello";
}

  @GetMapping
  public ResponseEntity<List<AdminDTO>> getAllAdmins() {
      List<AdminDTO> admins = adminService.getAllAdmins();
      return ResponseEntity.ok(admins);
  }
    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable int id) {
        AdminDTO admin = adminService.getAdminById(id);
        if (admin != null) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   @PostMapping
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody AdminDTO adminDTO) {
        AdminDTO createdAdmin = adminService.createAdmin(adminDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable int id, @RequestBody AdminDTO adminDTO) {
        AdminDTO updatedAdmin = adminService.updateAdmin(id, adminDTO);
        if (updatedAdmin != null) {
            return ResponseEntity.ok(updatedAdmin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable int id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
