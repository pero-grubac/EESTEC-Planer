package com.eestec.planer.controller.util;

import com.eestec.planer.dto.KorisnikDTO;

public class AuthResponse {

    private String token;
    private KorisnikDTO korisnikDTO;

    public AuthResponse(String token, KorisnikDTO korisnikDTO) {
        this.token = token;
        this.korisnikDTO = korisnikDTO;
    }

    public AuthResponse() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public KorisnikDTO getKorisnikDTO() {
        return korisnikDTO;
    }

    public void setKorisnikDTO(KorisnikDTO korisnikDTO) {
        this.korisnikDTO = korisnikDTO;
    }
}
