package com.tericcabrel.authapi.repositories;

import com.tericcabrel.authapi.entities.equb.EqubCategory;
import com.tericcabrel.authapi.entities.equb.EqubType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EqubTypeRepository extends CrudRepository<EqubType, Integer> {

}
