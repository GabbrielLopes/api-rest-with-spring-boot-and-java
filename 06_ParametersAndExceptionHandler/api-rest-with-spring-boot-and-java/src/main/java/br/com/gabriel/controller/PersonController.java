package br.com.gabriel.controller;

import br.com.gabriel.model.Person;
import br.com.gabriel.service.interfaces.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable("id") Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(personService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {

        return ResponseEntity.status(HttpStatus.OK).body(personService.findAll());
    }

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person) {

        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(person));
    }

    @PutMapping
    public ResponseEntity<Person> update(@RequestBody Person person) {

        return ResponseEntity.status(HttpStatus.OK).body(personService.update(person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        personService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}
