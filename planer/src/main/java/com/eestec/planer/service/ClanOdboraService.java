package com.eestec.planer.service;

import com.eestec.planer.dto.ClanOdboraDTO;
import java.util.List;
public interface ClanOdboraService {
    List<ClanOdboraDTO> getAllClanOdbora();

    ClanOdboraDTO getClanOdbora(Integer id);

    ClanOdboraDTO createClanOdbora(Integer id);

    boolean deleteClanOdbora(Integer id);

}
