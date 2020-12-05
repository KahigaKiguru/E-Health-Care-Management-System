package com.rajashekar.gande.EHealthManagementSystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rajashekar.gande.EHealthManagementSystem.model.Drug;

@Repository
public interface DrugRepository extends CrudRepository<Drug, Integer>{

}
