package com.eestec.planer.service;

import com.eestec.planer.dto.KategorijaDTO;
import com.eestec.planer.dto.KoordinatorDTO;

import java.util.List;

public interface KategorijaService
{
    public List<KategorijaDTO> getAllKategorije();
    public void createCategory(KategorijaDTO kategorijaDTO);

    public boolean deleteKategorija(Integer idKategorije);
    public KategorijaDTO getKategoriju(int idKategorija);
    public KategorijaDTO updateCategory(KategorijaDTO updatedCategory);
}
