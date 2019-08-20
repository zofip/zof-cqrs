package org.zof.cqrs.services.others;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zof.cqrs.entity.Transaction;
import org.zof.cqrs.entity.User;
import org.zof.cqrs.event.Command;
import org.zof.cqrs.repository.TransactionRepository;
import org.zof.cqrs.repository.UserRepository;
import org.zof.cqrs.utility.Field;
import org.zof.cqrs.utility.Table;
import org.zof.cqrs.utility.Type;

@Service
public class UserService implements IBaseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void send(Command command) {
        Type type = command.getType();
        if (type.equals(Type.CREATED)) {
            userRepository.save((User) command.getPayload());
            trackingCreate(type, (User) command.getPayload());
        } else if (type.equals(Type.UPDATE)) {
            trackingUpdate(type, (User) command.getPayload());
            userRepository.save((User) command.getPayload());
        }
    }

    private void trackingCreate(Type type, User user) {
        createTransaction(type, user, Field.NAME, user.getName());
        createTransaction(type, user, Field.LAST_NAME, user.getLastName());
        createTransaction(type, user, Field.EMAIL, user.getEmail());
    }

    private void trackingUpdate(Type type, User user) {
        User foundUser = userRepository.findById(user.getId()).get();
        if (!foundUser.getName().equals(user.getName())) createTransaction(type, user, Field.NAME, user.getName());
        if (!foundUser.getLastName().equals(user.getLastName()))
            createTransaction(type, user, Field.LAST_NAME, user.getLastName());
        if (!foundUser.getEmail().equals(user.getEmail())) createTransaction(type, user, Field.EMAIL, user.getEmail());
    }

    private void createTransaction(Type type, User user, Field field, String value) {
        Transaction transaction = new Transaction();
        transaction.setType(String.valueOf(type));
        transaction.setTableTransaction(String.valueOf(Table.USER));
        transaction.setFieldTable(String.valueOf(field));
        transaction.setValueTransaction(value);
        transaction.setUser(user);
        transactionRepository.save(transaction);
    }
}
