package org.zof.cqrs.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String tableTransaction;

    private String valueTransaction;

    private String fieldTable;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @PrePersist
    protected void onCreateDate() {
        createDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTableTransaction() {
        return tableTransaction;
    }

    public void setTableTransaction(String tableTransaction) {
        this.tableTransaction = tableTransaction;
    }

    public String getValueTransaction() {
        return valueTransaction;
    }

    public void setValueTransaction(String valueTransaction) {
        this.valueTransaction = valueTransaction;
    }

    public String getFieldTable() {
        return fieldTable;
    }

    public void setFieldTable(String fieldTable) {
        this.fieldTable = fieldTable;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
