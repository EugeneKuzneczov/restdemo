package exercise15.controller;

import exercise15.model.Person;
import exercise15.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PersonController {
    @Autowired
    PersonRepository repository;

    @GetMapping("people")
    public Iterable<Person> getPeople() {
        return repository.findAll();
    }

    @GetMapping("people/{id}")
    public Optional<Person> findPersonByID(@PathVariable("id") int id) {
        return repository.findById(id);
    }

    @PostMapping("people")
    public Person addPerson(@RequestBody Person person) {
        repository.save(person);
        return person;
    }

    @PutMapping("people/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") int id, @RequestBody Person person) {
        HttpStatus status = repository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        person.setId(id);
        return new ResponseEntity<>(repository.save(person), status);
    }

    @DeleteMapping("people/{id}")
    public void deletePerson(@PathVariable("id") int id) {
        repository.deleteById(id);
    }
}
