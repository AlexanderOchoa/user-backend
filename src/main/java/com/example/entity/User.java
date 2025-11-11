package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    private String idUser;
    private String name;
    private String email;
    private String password;
    private Integer state;
    private String token;
    private Date lastLogin;
    private Date creationDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Phone> phones;
}
