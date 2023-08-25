package com.eestec.P.eestecP.SuperUser;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperUserDAO extends  JpaRepository<SuperUserDTO,Integer>
{
}
