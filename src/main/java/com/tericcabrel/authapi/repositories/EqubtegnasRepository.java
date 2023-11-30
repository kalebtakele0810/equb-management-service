package com.tericcabrel.authapi.repositories;

import com.tericcabrel.authapi.entities.equb.StartEqub;
import com.tericcabrel.authapi.entities.equbtegna.Equbtegna;
import com.tericcabrel.authapi.entities.identity.Role;
import com.tericcabrel.authapi.entities.identity.RoleEnum;
import com.tericcabrel.authapi.entities.payment.Payments;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EqubtegnasRepository extends CrudRepository<Equbtegna, Integer> {
    @Query("select e from Equbtegna e where e.msisdn = ?1")
    Equbtegna findByMsisdn(String msisdn);

    @Query("select e from Equbtegna e inner join e.payments payments where payments.id = ?1")
    Equbtegna findByPayments_Id(Integer id);


}
