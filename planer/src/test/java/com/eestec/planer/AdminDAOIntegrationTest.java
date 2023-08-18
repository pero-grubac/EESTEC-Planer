package com.eestec.planer;

import com.eestec.planer.dao.AdminDAO;
import com.eestec.planer.dto.AdminDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AdminDAOIntegrationTest {

    @Autowired
    private AdminDAO adminDAO;

    @Test
    public void testGetAllAdmins() {
        List<AdminDTO> admins = adminDAO.getAllAdmin();


        for (AdminDTO admin : admins) {
            System.out.println("Admin ID: " + admin.getIdAdmin());
            System.out.println("Korisnicko Ime: " + admin.getKorisnickoIme());
            System.out.println("Lozinka: " + admin.getLozinka());
            System.out.println("-----------------------");
        }


    }

}
