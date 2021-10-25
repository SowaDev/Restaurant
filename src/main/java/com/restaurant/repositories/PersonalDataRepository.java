package com.restaurant.repositories;

import com.restaurant.model.PersonalData;
import org.springframework.data.repository.CrudRepository;

public interface PersonalDataRepository extends CrudRepository<PersonalData, Long> {
}
