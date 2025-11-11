package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "phone")
@Getter
@Setter
public class Phone {

    @Id
    private String iPhone;
    private String number;
    private String cityCode;
    private String contryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    private User user;
}
