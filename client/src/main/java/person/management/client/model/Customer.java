package person.management.client.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;



@Entity
@Data


public class Customer {
    @Id
    private int identification;
    private String password;
    private boolean status;
}
