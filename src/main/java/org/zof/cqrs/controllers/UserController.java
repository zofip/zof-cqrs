package org.zof.cqrs.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zof.cqrs.dto.UserDto;
import org.zof.cqrs.entity.User;
import org.zof.cqrs.services.command.CommandService;
import org.zof.cqrs.services.query.IUserQueryService;
import org.zof.cqrs.utility.Table;
import org.zof.cqrs.utility.Type;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private CommandService commandService;

    @Autowired
    private IUserQueryService queryService;

    private ModelMapper modelMapper = new ModelMapper();

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        commandService.emit(user, Table.USER, Type.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") long id, @RequestBody UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setId(id);
        commandService.emit(user, Table.USER, Type.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<UserDto> getS() {
        List<User> users = queryService.getS();
        return users.stream().map(
                user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }
}
