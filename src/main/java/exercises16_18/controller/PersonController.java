package exercises16_18.controller;

import exercises16_18.repository.PersonRepository;
import exercises16_18.models.Message;
import exercises16_18.models.Person;
import exercises16_18.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class PersonController {
    @Autowired
    PersonRepository repository;
    @Autowired
    PersonService service;

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

    //    Задание 16.  При добавлении сообщения реализовать проверку на наличие пользователя в БД
    @PostMapping("/people/{id}/messages")
    public ResponseEntity<Person> addMessage(@PathVariable("id") int id, @RequestBody Message message) {
        return service.addMessageToPerson(id, message);
    }

    //    Задание 17. Реализовать удаление сообщения у пользователя по его id
    @DeleteMapping("/people/{personId}/messages/{messageId}")
    public ResponseEntity<Person> deleteMessage(@PathVariable("personId") int personId, @PathVariable("messageId") int messageId) {
        return service.deleteMessageById(personId, messageId);
    }

    //    Задание 18.  Реализовать вывод списка сообщений у конкретного пользователя
    @GetMapping("/people/{id}/messages")
    public List<Message> findAllMessages(@PathVariable("id") int personId) {
        return service.findALLPersonMessages(personId);
    }
}
