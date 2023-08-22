package com.eestec.P.eestecP.Zahtjev;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZahtjevDAO extends JpaRepository<Zahtjev, Integer>
{

}
