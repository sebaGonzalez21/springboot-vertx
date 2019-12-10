package com.vertx.service;

import com.vertx.model.Person;

import java.util.List;

public interface IPersonService {
    List<Person> listPerson();
    Person savePerson(Person person);
}
