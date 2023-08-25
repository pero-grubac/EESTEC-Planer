package com.eestec.P.eestecP.Objave;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifikacija")
public class Objava {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  int IdObjava;


    @Column(name = "Sadržaj")
    String sadrzaj;

    @Column(name = "VrijemeKreiranja")
    LocalDateTime vrijemeKreiranja;

 //   @Column(name = "IdSuperusera")
  //   int idSuperusera;
}
