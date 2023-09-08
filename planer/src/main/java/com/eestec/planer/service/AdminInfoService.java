package com.eestec.planer.service;

import com.eestec.planer.config.AdminInfoDetails;
import com.eestec.planer.dao.AdminDAO;
import com.eestec.planer.dto.AdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminInfoService implements UserDetailsService {


    @Autowired
    private AdminDAO adminDAO;
    @Override
    public UserDetails loadUserByUsername(String korisnickoIme) throws UsernameNotFoundException {
       Optional<AdminDTO> adminDTO= adminDAO.findBykorisnickoIme(korisnickoIme);
        return adminDTO.map(AdminInfoDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("korisnik nije pronadjen "+korisnickoIme));

    }
}
