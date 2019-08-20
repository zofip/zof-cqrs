package org.zof.cqrs.services.query;

import org.springframework.stereotype.Service;
import org.zof.cqrs.entity.Person;
import org.zof.cqrs.repository.PersonRepository;

import java.util.List;

@Service
public class PersonQueryService implements  IPersonQueryService {

    private PersonRepository personRepository;

    public  PersonQueryService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getS() {
        return personRepository.findAll();
    }
}

