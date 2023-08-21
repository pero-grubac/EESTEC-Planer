package com.example.EestecPlaner.EestecPlaner.Zahtjev;

import com.example.EestecPlaner.EestecPlaner.Zahtjev.Zahtjev;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZahtjevDAO   extends JpaRepository<Zahtjev, Integer>
{

}
