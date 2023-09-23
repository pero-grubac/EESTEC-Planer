package com.eestec.planer.controller;

import com.eestec.planer.controller.util.AuthResponse;
import com.eestec.planer.controller.util.KorisnikRequest;
import com.eestec.planer.controller.util.LoginForm;
import com.eestec.planer.dto.AdminDTO;
import com.eestec.planer.service.AdminServiceImpl;
import com.eestec.planer.service.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admins")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
    private final AdminServiceImpl adminService; // Use AdminServiceImpl
    private final Logger logger = LoggerFactory.getLogger(KorisnikController.class);


    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }



//    @GetMapping("/getall")
//    //  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
//        List<AdminDTO> admins = adminService.getAllAdmins();
//        return ResponseEntity.ok(admins);
//    }


//    @PostMapping("/new")
//    //  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public ResponseEntity<Void> createAdmin(@RequestBody LoginForm loginForm) {
//        logger.info(loginForm.getUsername());
//        AdminDTO admin = adminService.createAdmin(loginForm);
//        if (admin != null)
//            return ResponseEntity.ok().build();
//        else return ResponseEntity.notFound().build();
//    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> updateAdmin(@RequestBody KorisnikRequest admin) {
        AdminDTO updatedAdmin = adminService.updateAdmin(admin);
        if (updatedAdmin != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getLozinka()));
        if (authentication.isAuthenticated())
            return ResponseEntity.ok(jwtService.generateToken(loginForm.getUsername()));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User " + loginForm.getUsername() + " not found");
    }


//    @DeleteMapping("/delete/{id}")
//    //  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer id) {
//        boolean isOk = adminService.deleteAdmin(id);
//        if (isOk)
//            return ResponseEntity.noContent().build();
//        else return ResponseEntity.notFound().build();
//    }

//    @PostMapping("/authenticate")
//    public String authenticateAndGetToken(@RequestBody LoginForm loginForm) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getLozinka(), loginForm.getUsername()));
//        if (authentication.isAuthenticated())
//            return jwtService.generateToken(loginForm.getUsername());
//        else
//            throw new UsernameNotFoundException("invalid user request !");
//
//    }

}
