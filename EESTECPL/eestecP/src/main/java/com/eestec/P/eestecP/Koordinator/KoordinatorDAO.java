package com.eestec.P.eestecP.Koordinator;

import com.eestec.P.eestecP.Korisnik.KorisnikDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KoordinatorDAO  extends JpaRepository<KoordinatorDTO, Integer>
{

}