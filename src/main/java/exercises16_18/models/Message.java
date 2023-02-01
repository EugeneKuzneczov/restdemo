package exercises16_18.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String text;
    private LocalDateTime time;
    @ManyToOne
    @JsonIgnore
    private Person person;

    public Message(String title, String text, LocalDateTime time, Person person) {
        this.title = title;
        this.text = text;
        this.person = person;
        this.time = time;
    }

    public Message(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
