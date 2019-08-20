package org.zof.cqrs.services.others;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zof.cqrs.entity.User;
import org.zof.cqrs.event.Command;
import org.zof.cqrs.repository.UserRepository;
import org.zof.cqrs.utility.Field;
import org.zof.cqrs.utility.Table;
import org.zof.cqrs.utility.Type;

@Service
public class UserService implements IBaseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ITransactionService transactionService;

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
        transactionService.createByTable(type, Table.USER, Field.NAME, user.getId(), user.getName());
        transactionService.createByTable(type, Table.USER, Field.LAST_NAME, user.getId(), user.getLastName());
        transactionService.createByTable(type, Table.USER, Field.EMAIL, user.getId(), user.getEmail());
    }

    private void trackingUpdate(Type type, User user) {
        User foundUser = userRepository.findById(user.getId()).get();
        if (!foundUser.getName().equals(user.getName()))
            transactionService.createByTable(type, Table.USER, Field.NAME, user.getId(), user.getName());
        if (!foundUser.getLastName().equals(user.getLastName()))
            transactionService.createByTable(type, Table.USER, Field.LAST_NAME, user.getId(), user.getLastName());
        if (!foundUser.getEmail().equals(user.getEmail()))
            transactionService.createByTable(type, Table.USER, Field.EMAIL, user.getId(), user.getEmail());
    }

}
