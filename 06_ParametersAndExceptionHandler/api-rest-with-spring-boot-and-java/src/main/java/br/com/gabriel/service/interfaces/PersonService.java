package br.com.gabriel.service.interfaces;

import br.com.gabriel.model.Person;

import java.util.List;

public interface PersonService {

    Person findById(Long id);

    List<Person> findAll();

    Person create(Person person);

    Person update(Person person);

    void delete(long id);
}
