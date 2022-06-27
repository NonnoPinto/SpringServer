package com.tut1.tut1.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.tut1.tut1.model.Person;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{
    
    private static List<Person> DB = new ArrayList<>();
    
    @Override
    public int insertPerson(UUID id, Person person){
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> PersonMaybe = selectPersonById(id);
        if(PersonMaybe.isEmpty())
            return 0;
        
        DB.remove(PersonMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return selectPersonById(id)
            .map(p->{
                int indexOfPersonToDelete = DB.indexOf(person);
                if (indexOfPersonToDelete >= 0){
                    DB.set(indexOfPersonToDelete, person);
                    System.out.println("Ho sostituito la persona con id " + id);
                    return 1;
                }
                System.out.println("Non ho sostituito " + person.getId() + " " + person.getName());
                return 0;
            })
        .orElse(0);
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
            .filter(person -> person.getId().equals(id)).findFirst();
    }

}
