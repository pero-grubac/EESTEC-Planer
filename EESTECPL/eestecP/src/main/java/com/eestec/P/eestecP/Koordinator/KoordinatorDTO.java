package com.eestec.P.eestecP.Koordinator;

import com.eestec.P.eestecP.SuperUser.SuperUserDTO;
import jakarta.persistence.*;

public class KoordinatorDTO extends SuperUserDTO
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int IdKorisnika;
    @OneToOne(mappedBy = "author", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private SuperUserDTO superUserDTO;




}
