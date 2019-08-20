package org.zof.cqrs.services.others;

import org.springframework.beans.factory.annotation.Autowired;
import org.zof.cqrs.entity.Person;
import org.zof.cqrs.entity.Transaction;
import org.zof.cqrs.event.Command;
import org.zof.cqrs.repository.PersonRepository;
import org.zof.cqrs.repository.TransactionRepository;
import org.zof.cqrs.utility.Field;
import org.zof.cqrs.utility.Table;
import org.zof.cqrs.utility.Type;

public class PersonService implements IBaseService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TransactionRepository transactionRepository;

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
        createTransaction(type, person, Field.NAME, person.getName());
    }

    private void trackingUpdate(Type type, Person person) {
        Person foundPerson = personRepository.findById(person.getId()).get();
        if (!foundPerson.getName().equals(person.getName())) createTransaction(type, person, Field.NAME, person.getName());
    }

    private void createTransaction(Type type, Person person, Field field, String value) {
        Transaction transaction = new Transaction();
        transaction.setType(String.valueOf(type));
        transaction.setTableTransaction(String.valueOf(Table.USER));
        transaction.setFieldTable(String.valueOf(field));
        transaction.setValueTransaction(value);
//        transaction.setUser(user);
        transactionRepository.save(transaction);
    }
}
