package org.zof.cqrs.services.others;

import org.zof.cqrs.event.Command;

public interface IBaseService {
    void send(Command command);
}
