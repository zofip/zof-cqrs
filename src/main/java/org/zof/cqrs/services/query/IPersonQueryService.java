package org.zof.cqrs.services.query;

import org.zof.cqrs.entity.Person;

import java.util.List;

public interface IPersonQueryService {
    List<Person> getS();
}
