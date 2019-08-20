package org.zof.cqrs.services.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zof.cqrs.event.Command;
import org.zof.cqrs.utility.GeneralConstants;
import org.zof.cqrs.utility.Table;
import org.zof.cqrs.utility.Type;
import reactor.bus.Event;
import reactor.bus.EventBus;

@Service
public class CommandService implements ICommandService {

    @Autowired
    private EventBus eventBus;

    @Override
    public void emit(Object payload, Table table, Type type) {
        Command command = new Command();
        command.setTable(table);
        command.setType(type);
        command.setPayload(payload);
        eventBus.notify(GeneralConstants.TRANSACTION_CONSUMER, Event.wrap(command));
    }
}
