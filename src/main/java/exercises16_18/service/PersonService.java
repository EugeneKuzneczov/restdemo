package exercises16_18.service;

import exercises16_18.models.Message;
import exercises16_18.models.Person;
import exercises16_18.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepository repository;

    public ResponseEntity<Person> addMessageToPerson(int personId, Message message) {
        if (repository.existsById(personId) && repository.findById(personId).isPresent()) {
            Person person = repository.findById(personId).get();
            message.setPerson(person);
            message.setTime(LocalDateTime.now());
            person.addMessage(message);
            return new ResponseEntity<>(repository.save(person), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Person> deleteMessageById(int personId, int messageId) {
        if (repository.existsById(personId) && repository.findById(personId).isPresent()) {
            Person person = repository.findById(personId).get();
            List<Message> messageList = person.getMessages();
            messageList.removeIf(message -> message.getId() == messageId);
            return new ResponseEntity<>(repository.save(person), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public List<Message> findALLPersonMessages(int personId) {
        List<Message> messageList = new ArrayList<>();
        if (repository.findById(personId).isPresent()) {
            Person person = repository.findById(personId).get();
            messageList = person.getMessages();
        }
        return messageList;
    }
}
