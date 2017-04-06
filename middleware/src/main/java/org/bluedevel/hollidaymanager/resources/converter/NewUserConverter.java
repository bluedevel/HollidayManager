package org.bluedevel.hollidaymanager.resources.converter;

import org.bluedevel.hollidaymanager.models.User;
import org.bluedevel.hollidaymanager.resources.dto.NewUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Robin Engel
 */
@Component
public class NewUserConverter implements DtoConverter<NewUserDto, User> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public NewUserDto toDto(User model) {
        throw new NotImplementedException();
    }

    @Override
    public User toModel(NewUserDto dto) {
        User model = userConverter.toModel(dto);
        model.setPassword(dto.getPassword());
        return model;
    }
}
