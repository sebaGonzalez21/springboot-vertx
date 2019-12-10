package com.vertx.imp;

import com.vertx.model.Person;
import com.vertx.repository.PersonRepository;
import com.vertx.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonImp implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> listPerson() {
        List<Person> personLocal = null;
        try {
            personLocal = personRepository.findAll();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return personLocal;
    }

    @Override
    public Person savePerson(Person person) {
        Person personLocal = null;
        try {
            personLocal = personRepository.save(person);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return personLocal;
    }
}
