package com.vertx.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Persons")
public class Person {
    @Id
    @Column(name = "id_person")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    @NotNull
    private String namePerson;

    @Column(name = "last_name")
    @NotNull
    private String lastNamePerson;

    @Column(name = "rut")
    @NotNull
    private String rutPerson;

    @Column(name = "email")
    @NotNull
    @Email
    private String emailPerson;

    @Column(name = "cell_phone")
    @NotNull
    private String cellPhonePerson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    public String getLastNamePerson() {
        return lastNamePerson;
    }

    public void setLastNamePerson(String lastNamePerson) {
        this.lastNamePerson = lastNamePerson;
    }

    public String getRutPerson() {
        return rutPerson;
    }

    public void setRutPerson(String rutPerson) {
        this.rutPerson = rutPerson;
    }

    public String getEmailPerson() {
        return emailPerson;
    }

    public void setEmailPerson(String emailPerson) {
        this.emailPerson = emailPerson;
    }

    public String getCellPhonePerson() {
        return cellPhonePerson;
    }

    public void setCellPhonePerson(String cellPhonePerson) {
        this.cellPhonePerson = cellPhonePerson;
    }
}
