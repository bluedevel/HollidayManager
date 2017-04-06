package org.bluedevel.hollidaymanager.resources.converter;

import org.bluedevel.hollidaymanager.models.User;
import org.bluedevel.hollidaymanager.resources.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Robin Engel
 */
@Component
public class UserConverter implements DtoConverter<UserDto, User> {

    @Autowired
    private UserWorkdayDefinitionConverter userWorkdayDefinitionConverter;

    @Override
    public UserDto toDto(User model) {
        UserDto dto = new UserDto();
        dto.setUsername(model.getUsername());
        dto.setVacationDays(model.getVacationDays());
        dto.setDepartment(model.getDepartment());
        dto.setRole(model.getRole());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setWorkdayDefinition(
                userWorkdayDefinitionConverter.toDto(model.getUserWorkdayDefinition()));
        return dto;
    }

    @Override
    public User toModel(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setVacationDays(dto.getVacationDays());
        user.setDepartment(dto.getDepartment());
        user.setRole(dto.getRole());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUserWorkdayDefinition(
                userWorkdayDefinitionConverter.toModel(dto.getWorkdayDefinition()));
        return user;
    }
}
