package org.zof.cqrs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zof.cqrs.entity.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
