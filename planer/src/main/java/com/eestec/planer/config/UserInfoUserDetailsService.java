package com.eestec.planer.config;

import com.eestec.planer.dao.*;
import com.eestec.planer.dto.AdminDTO;
import com.eestec.planer.dto.ClanOdboraDTO;
import com.eestec.planer.dto.KoordinatorDTO;
import com.eestec.planer.dto.KorisnikDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminDAO adminDAO;
    private final Logger logger = LoggerFactory.getLogger(UserInfoUserDetailsService.class);
    @Autowired
    private KorisnikDAO korisnikDAO;

    @Autowired
    private KoordinatorDAO koordinatorDAO;

    @Autowired
    private ClanOdboraDAO clanOdboraDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AdminDTO> admin = adminDAO.findBykorisnickoIme(username);
        if (admin.isPresent())
            return admin.map(AdminInfoDetails::new)
                    .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

        Optional<KorisnikDTO> korisnik = korisnikDAO.findBykorisnickoIme(username);
        // provjera da li je koordinator ili clanodbora ili superuser sta li vec
        if (korisnik.isPresent()) {
            Optional<KoordinatorDTO> koordinator = koordinatorDAO.findBykorisnickoIme(username);
            if(koordinator.isPresent()) {
                korisnik.get().setRole(koordinator.get().getUloga());
            }
            Optional<ClanOdboraDTO> clanOdbora = clanOdboraDAO.findBykorisnickoIme(username);
            if(clanOdbora.isPresent()) {
                korisnik.get().setRole(clanOdbora.get().getUloga());

            }
            return korisnik.map(KorisnikInfoDetails::new)
                    .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
        }
        return null;
    }
}
