package org.zof.cqrs.event;

import org.zof.cqrs.utility.Table;
import org.zof.cqrs.utility.Type;

public class Command {

    private Table table;
    private Type type;
    private Object payload;

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object user) {
        this.payload = user;
    }
}
