package br.com.gabriel.controller;

import br.com.gabriel.service.interfaces.PersonService;
import br.com.gabriel.vo.v1.PersonVO;
import br.com.gabriel.vo.v2.PersonVOV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    public ResponseEntity<PersonVO> findById(@PathVariable("id") Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(personService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PersonVO>> findAll() {

        return ResponseEntity.status(HttpStatus.OK).body(personService.findAll());
    }

    @PostMapping
    public ResponseEntity<PersonVO> create(@RequestBody PersonVO person) {

        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(person));
    }

    @PostMapping("/v2")
    public ResponseEntity<PersonVOV2> createV2(@RequestBody PersonVOV2 person) {

        return ResponseEntity.status(HttpStatus.CREATED).body(personService.createV2(person));
    }

    @PutMapping
    public ResponseEntity<PersonVO> update(@RequestBody PersonVO person) {

        return ResponseEntity.status(HttpStatus.OK).body(personService.update(person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        personService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
