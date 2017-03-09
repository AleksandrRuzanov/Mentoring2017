package com.epam.mentoring.person;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Aleksandr_Ruzanov on 28.02.2017.
 */

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
