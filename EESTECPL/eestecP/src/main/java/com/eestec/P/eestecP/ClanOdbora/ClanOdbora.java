package com.eestec.P.eestecP.ClanOdbora;

import com.eestec.P.eestecP.Koordinator.KoordinatorDTO;
import com.eestec.P.eestecP.Korisnik.KorisnikDTO;
import com.eestec.P.eestecP.SuperUser.SuperUserDTO;
import jakarta.persistence.*;

public class ClanOdbora extends SuperUserDTO {


    @Id
    @Column(name= "IdClan")
    protected  int IdClana;

    @OneToOne
    @MapsId
    @PrimaryKeyJoinColumn(name="idsuperuser")
    private SuperUserDTO superUserDTO;

}
