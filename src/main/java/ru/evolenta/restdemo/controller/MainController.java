package ru.evolenta.restdemo.controller;

import org.springframework.web.bind.annotation.*;
import ru.evolenta.restdemo.entity.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class MainController {
    private List<Message> messageList = new ArrayList<>(Arrays.asList(
            new Message(1, "Spring Framework",
                    "Spring Framework — универсальный фреймворк с открытым исходным кодом для Java-платформы",
                    LocalDateTime.of(2022, 1, 29, 15, 24, 34)),
            new Message(2, "Java",
                    "Java — строго типизированный объектно-ориентированный язык программирования общего назначения",
                    LocalDateTime.of(2020, 5, 30, 18, 1, 2)),
            new Message(3, "Linux",
                    "Linux — семейство Unix-подобных операционных систем на базе ядра Linux",
                    LocalDateTime.of(2021, 10, 4, 9, 44, 32)),
            new Message(4, "Oracle",
                    "Oracle — американская корпорация, крупнейший производитель программного обеспечения",
                    LocalDateTime.of(2022, 6, 4, 21, 1, 4))
    ));

    @GetMapping("messages")
    public Iterable<Message> getMessageList() {
        return messageList;
    }

    @GetMapping("messages/{id}")
    public Optional<Message> findMessageById(@PathVariable("id") int id) {
        return messageList.stream().filter(message -> message.getId() == id).findFirst();
    }

    @PostMapping("messages")
    public Message addMessage(@RequestBody Message message) {
        messageList.add(message);
        return message;
    }

    @PutMapping("messages/{id}")
    public Message updateMessage(@PathVariable("id") int id, @RequestBody Message message) {
        int index = -1;
        for (Message msg : messageList) {
            if (msg.getId() == id) {
                index = messageList.indexOf(msg);
                messageList.set(index, message);
            }
        }
        return index == -1 ? addMessage(message) : message;
    }

    @DeleteMapping("messages/{id}")
    public boolean deleteMessage(@PathVariable("id") int id) {
        return messageList.removeIf(message -> message.getId() == id);
    }
}
