package person.management.client.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;





@Entity
@Data

public class Person {
    @Id
    private int identification;
    private String name;
    private char gender;
    private String address;
    private String phone;
}