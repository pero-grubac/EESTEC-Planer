package com.eestec.planer.json;
import  com.eestec.planer.dto.AdminDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

public interface AdminDTOMixin {
    @JsonIgnore
    int getIdAdmin();


    String getKorisnickoIme();
    String getLozinka();
    String getRole();
}
