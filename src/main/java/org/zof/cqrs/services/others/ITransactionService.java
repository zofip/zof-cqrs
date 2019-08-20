package org.zof.cqrs.services.others;

import org.zof.cqrs.utility.Field;
import org.zof.cqrs.utility.Table;
import org.zof.cqrs.utility.Type;

public interface ITransactionService {
    void createByTable(Type type, Table table, Field field, Long recordId, String value);
}
