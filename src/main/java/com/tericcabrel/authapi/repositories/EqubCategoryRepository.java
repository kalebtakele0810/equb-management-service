package com.tericcabrel.authapi.repositories;

import com.tericcabrel.authapi.entities.equb.Equb;
import com.tericcabrel.authapi.entities.equb.EqubCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EqubCategoryRepository extends CrudRepository<EqubCategory, Integer> {
    @Query("select e from EqubCategory e where e.name = ?1")
    List<EqubCategory> findByName(String name);

}
