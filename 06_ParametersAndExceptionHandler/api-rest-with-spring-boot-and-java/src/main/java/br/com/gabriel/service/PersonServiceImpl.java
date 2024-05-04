package br.com.gabriel.service;

import br.com.gabriel.exception.ResourceNotFoundException;
import br.com.gabriel.model.Person;
import br.com.gabriel.repository.PersonRepository;
import br.com.gabriel.service.interfaces.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());


    @Override
    public Person findById(Long id) {
        logger.info("Finding a person...");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    @Override
    public List<Person> findAll() {
        logger.info("Finding all people!");
        return repository.findAll();
    }

    @Override
    @Transactional
    public Person create(Person person) {
        logger.info("Creating one person!");

        return repository.save(person);
    }

    @Override
    public Person update(Person person) {
        logger.info("Updating one person!");

        Person personDBResponse = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        personDBResponse.setFirstName(person.getFirstName());
        personDBResponse.setLastName(person.getLastName());
        personDBResponse.setAddress(person.getAddress());
        personDBResponse.setGender(person.getGender());

        return repository.save(personDBResponse);
    }

    @Override
    public void delete(long id) {
        logger.info("Deleting one person!");

        Person personDBResponse = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(personDBResponse);
    }

}
