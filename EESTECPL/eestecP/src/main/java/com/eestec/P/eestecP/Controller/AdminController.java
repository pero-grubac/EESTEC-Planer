package com.eestec.P.eestecP.Controller;

import com.eestec.P.eestecP.Admin.AdminDTO;
import com.eestec.P.eestecP.Service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public String hello() {
        return "hello";
    }

    @GetMapping("/getall")
    //  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        List<AdminDTO> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }


    @PostMapping("/new")
    //  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody AdminDTO adminDTO) {
        AdminDTO admin = adminService.createAdmin(adminDTO);
        if (admin != null)
            return ResponseEntity.ok(admin);
        else return ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    //  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AdminDTO> updateAdmin(@RequestBody AdminDTO adminDTO) {
        AdminDTO updatedAdmin = adminService.updateAdmin(adminDTO);
        if (updatedAdmin != null) {
            return ResponseEntity.ok(updatedAdmin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    //  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer id) {
        boolean isOk = adminService.deleteAdmin(id);
        if (isOk)
            return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }
}
