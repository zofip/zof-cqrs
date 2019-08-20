package org.zof.cqrs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zof.cqrs.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
