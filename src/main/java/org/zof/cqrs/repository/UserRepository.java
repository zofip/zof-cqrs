package org.zof.cqrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zof.cqrs.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
