package com.eestec.P.eestecP.Korisnik;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikDAO   extends JpaRepository<KorisnikDTO, Integer>
{

}