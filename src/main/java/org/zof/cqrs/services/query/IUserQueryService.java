package org.zof.cqrs.services.query;

import org.zof.cqrs.entity.User;

import java.util.List;

public interface IUserQueryService {

    List<User> getS();

    long countAllUsers();
}
