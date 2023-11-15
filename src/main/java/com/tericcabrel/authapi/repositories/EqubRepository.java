package com.tericcabrel.authapi.repositories;

import com.tericcabrel.authapi.entities.equb.Equb;
import com.tericcabrel.authapi.entities.identity.Role;
import com.tericcabrel.authapi.entities.identity.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EqubRepository extends CrudRepository<Equb, Integer> {

}
