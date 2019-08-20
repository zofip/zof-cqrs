package org.zof.cqrs.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zof.cqrs.dto.PersonDto;
import org.zof.cqrs.entity.Person;
import org.zof.cqrs.services.command.CommandService;
import org.zof.cqrs.services.query.IPersonQueryService;
import org.zof.cqrs.utility.Table;
import org.zof.cqrs.utility.Type;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/api/persons")
public class PersonController {

    @Autowired
    private CommandService commandService;

    @Autowired
    private IPersonQueryService queryService;

    private ModelMapper modelMapper = new ModelMapper();

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody PersonDto personDto) {
        Person person =  modelMapper.map(personDto, Person.class);
        commandService.emit(person, Table.PERSON, Type.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<PersonDto> getS() {
        List<Person> personS = queryService.getS();
        return personS.stream().map(
                person -> modelMapper.map(person, PersonDto.class)).collect(Collectors.toList());
    }

}
