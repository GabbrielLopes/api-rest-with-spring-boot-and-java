package br.com.gabriel.service;

import br.com.gabriel.model.Person;
import br.com.gabriel.service.interfaces.PersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServiceImpl implements PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());


    @Override
    public Person findById(Long id) {

        logger.info("Finding a person...");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("firstName");
        person.setLastName("lastName");
        person.setAddress("Adress");
        person.setGender("Male");

        return person;
    }

    @Override
    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

    @Override
    public Person create(Person person) {
        logger.info("Person created!");
        return  person;
    }

    @Override
    public Person update(Person person) {
        logger.info("Person updated!");
        return  person;
    }

    @Override
    public void delete(long id) {
        logger.info("Person deleted!");
    }


    private Person mockPerson(int i) {
         //new Person(counter.incrementAndGet(), "firstName" + i, "lastName", "address", "Male");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("firstName" + i);
        person.setLastName("lastName" + i);
        person.setAddress("Address");
        person.setGender("Male");

        return person;
    }
}
