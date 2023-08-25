package org.company.project.model.service;

import org.company.project.model.domain.Person;
import org.company.project.model.repository.PersonDA;

import java.util.List;

public class PersonService {
    private static final PersonService PERSON_SERVICE = new PersonService();
    private PersonService () {}

    public static PersonService getInstance() {
        return PERSON_SERVICE;
    }

    public void save (Person person) throws Exception {
        person.setSalary(person.getSalary() - (person.getSalary()*10/100));
        try (PersonDA personDA = new PersonDA()){
            personDA.insert(person);
            personDA.commit();
        }
    }
    public void change (Person person) throws Exception {
        try(PersonDA personDA = new PersonDA()){
            personDA.update(person);
            personDA.commit();
        }
    }
    public void remove (Person person) throws Exception {
        try(PersonDA personDA = new PersonDA()){
            personDA.delete(person);
            personDA.commit();
        }
    }
    public Person findOne (Person person) throws Exception {
        try(PersonDA personDA = new PersonDA()){
            return personDA.selectOne(person);
        }
    }
    public List<Person> findAll () throws Exception {
        try(PersonDA personDA = new PersonDA()) {
            return personDA.selectAll();
        }
    }
}
