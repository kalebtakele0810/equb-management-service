package com.tericcabrel.authapi.repositories;

import com.tericcabrel.authapi.entities.payment.ChannelEnum;
import com.tericcabrel.authapi.entities.payment.Payments;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends CrudRepository<Payments, Integer> {

}
