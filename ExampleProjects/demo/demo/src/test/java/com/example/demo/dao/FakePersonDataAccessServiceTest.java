package com.example.demo.dao;

import com.example.demo.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FakePersonDataAccessServiceTest {

    private FakePersonDataAccessService underTest;

    @BeforeEach
    void setUp() {
        underTest = new FakePersonDataAccessService();
    }

    @Test
    public void canPerformCrud() {
        UUID idOne = UUID.randomUUID();
        Person personOne = new Person(idOne,"Batman");

        // ...And Anna Smith aged 40
        UUID idTwo = UUID.randomUUID();
        Person personTwo = new Person(idTwo, "Anna Smith");

        underTest.addPerson(personOne);
        underTest.addPerson(personTwo);

        // When Batman and Anna added to db
        assertThat(underTest.selectPersonById(idOne));
        assertThat(underTest.selectPersonById(idTwo));

        assertThat(underTest.selectPersonById(idOne))
                            .isPresent()
                            .hasValueSatisfying(personFromDb -> assertThat(personFromDb).isEqualToComparingFieldByField(personOne));
        assertThat(underTest.selectPersonById(idTwo))
                .isPresent()
                .hasValueSatisfying(personFromDb -> assertThat(personFromDb).isEqualToComparingFieldByField(personTwo));

        List<Person> people = underTest.selectAllPeople();

        assertThat(people)
                .hasSize(2)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(personOne,personTwo);

        // ... An update request (James Bond name to Jake Black)
        Person personUpdate = new Person(idOne, "Jake Black");

        assertThat(underTest.updatePersonById(idOne,personUpdate)).isEqualTo(1);

        // Then when get person with idOne then should have name as James Bond > Jake Black
        assertThat(underTest.selectPersonById(idOne))
                        .isPresent()
                        .hasValueSatisfying(personFromDb -> assertThat(personFromDb).isEqualToComparingFieldByField(personUpdate));

        assertThat(underTest.deletePerson(idOne)).isEqualTo(1);

        assertThat(underTest.selectPersonById(idOne)).isEmpty();

        assertThat(underTest.selectAllPeople())
                .hasSize(1)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(personTwo);


    }

    @Test
    public void willReturn0IfNoPersonFoundToDelete() {
        // Given
        UUID id = UUID.randomUUID();

        // When
        int deleteResult = underTest.deletePerson(id);

        // Then
        assertThat(deleteResult).isEqualTo(0);
    }

    @Test
    public void willReturn0IfNoPersonFoundToUpdate() {
        // Given
        UUID id = UUID.randomUUID();
        Person person = new Person(id, "James Not In Db");

        // When
        int deleteResult = underTest.updatePersonById(id, person);

        // Then
        assertThat(deleteResult).isEqualTo(0);
    }
}