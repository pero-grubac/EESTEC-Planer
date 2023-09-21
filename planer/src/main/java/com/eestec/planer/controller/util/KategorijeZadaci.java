package com.eestec.planer.controller.util;

import com.eestec.planer.dto.KategorijaDTO;
import com.eestec.planer.dto.ZadatakDTO;

import java.util.ArrayList;
import java.util.List;

public class KategorijeZadaci {
    private List<KategorijaDTO> kategorije=new ArrayList<>();
    private List<ZadatakDTO> zadaci= new ArrayList<>();

    public KategorijeZadaci() {
    }

    public List<KategorijaDTO> getKategorije() {
        return kategorije;
    }

    public void setKategorije(List<KategorijaDTO> kategorije) {
        this.kategorije = kategorije;
    }

    public List<ZadatakDTO> getZadaci() {
        return zadaci;
    }

    public void setZadaci(List<ZadatakDTO> zadaci) {
        this.zadaci = zadaci;
    }
}
