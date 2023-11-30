package com.tericcabrel.authapi.repositories;

import com.tericcabrel.authapi.entities.equb.Equb;
import com.tericcabrel.authapi.entities.identity.Role;
import com.tericcabrel.authapi.entities.identity.RoleEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EqubRepository extends CrudRepository<Equb, Integer> {
    @Query("select e from Equb e inner join e.equbtegnas equbtegnas where equbtegnas.msisdn = ?1")
    Equb findByEqubtegnas_Msisdn(String msisdn);

    @Query("select e from Equb e inner join e.payments payments where payments.id = ?1")
    Equb findByPayments_Id(Integer id);

    @Query("select e from Equb e inner join e.startEqubs startEqubs where startEqubs.id = ?1")
    Equb findByStartEqubs_Id(Integer id);
    
}
