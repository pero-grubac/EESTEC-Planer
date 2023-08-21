package com.example.EestecPlaner.EestecPlaner.Korisnik;


import com.example.EestecPlaner.EestecPlaner.Korisnik.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikDAO   extends JpaRepository<Korisnik, Integer>
{

}