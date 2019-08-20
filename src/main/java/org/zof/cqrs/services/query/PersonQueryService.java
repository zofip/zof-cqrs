package org.zof.cqrs.services.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zof.cqrs.entity.Person;
import org.zof.cqrs.repository.PersonRepository;

import java.util.List;

@Service
public class PersonQueryService implements IPersonQueryService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getS() {
        return personRepository.findAll();
    }
}

