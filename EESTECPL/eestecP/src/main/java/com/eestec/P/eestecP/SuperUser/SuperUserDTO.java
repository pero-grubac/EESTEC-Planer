package com.eestec.P.eestecP.SuperUser;

import com.eestec.P.eestecP.Korisnik.KorisnikDTO;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name = "superuser")
@PrimaryKeyJoinColumn(name = "IdSuperuser")
@DiscriminatorValue("superuser")
public class SuperUserDTO extends KorisnikDTO {


}