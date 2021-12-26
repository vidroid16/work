package com.example.demo.database.entity;

import com.example.demo.security.models.Role;
import com.example.demo.security.models.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity(name = "accounts")
public class Account {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;

    public Account(String username, String password) {
        this.username = username;
        this.password = new BCryptPasswordEncoder(12).encode(password);
        this.status = Status.ACTIVE;
        this.role = Role.USER;
    }
}
