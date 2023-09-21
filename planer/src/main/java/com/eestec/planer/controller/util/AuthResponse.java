package com.eestec.planer.controller.util;

import com.eestec.planer.dto.KorisnikDTO;

public class AuthResponse {

    private String token;
    private KorisnikDTO korisnik;

    public AuthResponse(String token, KorisnikDTO korisnikDTO) {
        this.token = token;
        this.korisnik = korisnikDTO;
    }

    public AuthResponse() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public KorisnikDTO getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(KorisnikDTO korisnik) {
        this.korisnik = korisnik;
    }
}
