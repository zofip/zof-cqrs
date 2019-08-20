package org.zof.cqrs.services.others;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zof.cqrs.entity.Transaction;
import org.zof.cqrs.repository.TransactionRepository;
import org.zof.cqrs.utility.Field;
import org.zof.cqrs.utility.Table;
import org.zof.cqrs.utility.Type;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void createByTable(Type type, Table table, Field field, Long recordId, String value) {
        Transaction transaction = new Transaction();
        transaction.setType(String.valueOf(type));
        transaction.setTableTransaction(String.valueOf(table));
        transaction.setFieldTable(String.valueOf(field));
        transaction.setValueTransaction(value);
        transaction.setTableRecordId(recordId);
        transactionRepository.save(transaction);
    }
}
