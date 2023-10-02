package com.control_2_tbd.repositories;

import com.control_2_tbd.entities.TareaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends CrudRepository<TareaEntity, Long> {

}
