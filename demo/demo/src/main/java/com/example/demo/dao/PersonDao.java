package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    int insertPeron(UUID id, Person person);

    default int addPerson(Person person){

        UUID id = UUID.randomUUID();
        return insertPeron(id, person);
    }

    List<Person> selectAllPeople();

    Optional<Person> selectPersonById(UUID id);

    int deletePerson(UUID id);

    int updatePersonById(UUID id, Person person);
}
