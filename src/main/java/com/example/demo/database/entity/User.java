package com.example.demo.database.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "SURNAME", nullable = false)
    private String surname;
    @Column(name = "PATRONTMIC")
    private String patron;
    @Column(name = "BIRTHDAY", nullable = false)
    @JsonFormat(pattern="dd.MM.yyyy")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Column(name = "EMAIL", nullable = false)
    @Email(message = "Incorrect email")
    private String email;
    @Column(name = "PHONE")
    @Pattern(regexp = "^((\\+7|7|8)+([0-9]){10})$")
    private String phone;

    public User(String name, String surname, String surname1, String patron, Date birthday, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.surname = surname1;
        this.patron = patron;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
    }
}
