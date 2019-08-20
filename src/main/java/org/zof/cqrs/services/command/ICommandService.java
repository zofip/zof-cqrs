package org.zof.cqrs.services.command;

import org.zof.cqrs.utility.Table;
import org.zof.cqrs.utility.Type;

public interface ICommandService {
    void emit(Object payload, Table table, Type type);
}
