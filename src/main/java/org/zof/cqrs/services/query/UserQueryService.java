package org.zof.cqrs.services.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zof.cqrs.entity.User;
import org.zof.cqrs.repository.UserRepository;


import java.util.List;

@Service
public class UserQueryService implements IUserQueryService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getS() {
        return userRepository.findAll();
    }
}
