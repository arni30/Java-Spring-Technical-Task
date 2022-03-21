package task.javaspringtechnicaltask.models.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
//@ToString
public class Account {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal balance;

    private String name;

    private String surname;

    public Account(String name, String surname, BigDecimal balance) {
        this.name = name;
        this.surname = surname;
        this.balance = balance;
    }

    public Account() {

    }
}
