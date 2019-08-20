package org.zof.cqrs.services.others;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zof.cqrs.entity.Person;
import org.zof.cqrs.event.Command;
import org.zof.cqrs.repository.PersonRepository;
import org.zof.cqrs.utility.Field;
import org.zof.cqrs.utility.Table;
import org.zof.cqrs.utility.Type;

@Service
public class PersonService implements IBaseService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ITransactionService transactionService;

    @Override
    public void send(Command command) {
        Type type = command.getType();
        if (type.equals(Type.CREATED)) {
            personRepository.save((Person) command.getPayload());
            trackingCreate(type, (Person) command.getPayload());
        } else if (type.equals(Type.UPDATE)) {
            trackingUpdate(type, (Person) command.getPayload());
            personRepository.save((Person) command.getPayload());
        }
    }

    private void trackingCreate(Type type, Person person) {
        transactionService.createByTable(type, Table.PERSON, Field.NAME, person.getId(), person.getName());
    }

    private void trackingUpdate(Type type, Person person) {
        Person foundPerson = personRepository.findById(person.getId()).get();
        if (!foundPerson.getName().equals(person.getName()))
            transactionService.createByTable(type, Table.PERSON, Field.NAME, person.getId(), person.getName());
    }

}
