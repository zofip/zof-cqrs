package org.zof.cqrs.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zof.cqrs.services.others.IBaseService;
import org.zof.cqrs.services.others.PersonService;
import org.zof.cqrs.services.others.UserService;
import org.zof.cqrs.utility.Table;
import reactor.bus.Event;
import reactor.fn.Consumer;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommandListener implements Consumer<Event<Command>> {

    private Map<Table, IBaseService> map;

    @Autowired
    private UserService userService;

    @Autowired
    private PersonService personService;

    @Override
    public void accept(Event<Command> obj) {
        Command command = obj.getData();
        IBaseService service = getInstance(command.getTable());
        service.send(command);
    }

    public IBaseService getInstance(Table type) {
        init();
        return map.get(type);
    }

    private void init() {
        map = new HashMap<>();
        map.put(Table.USER, userService);
        map.put(Table.PERSON, personService);
    }
}
