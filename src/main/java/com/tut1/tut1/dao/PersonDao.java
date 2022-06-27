package com.tut1.tut1.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.tut1.tut1.model.Person;

public interface PersonDao {
    
    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    List<Person> selectAllPeople();
    
    int deletePersonById(UUID id);

    int updatePersonById(UUID id, Person person);

    Optional<Person> selectPersonById(UUID id);

}
