package dev.remitano.core.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends GenericEntity {

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

}

