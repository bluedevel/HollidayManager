package org.bluedevel.hollidaymanager.resources.converter;

import org.bluedevel.hollidaymanager.models.UserWorkdayDefinition;
import org.bluedevel.hollidaymanager.resources.dto.WorkdayDefinitionDto;
import org.springframework.stereotype.Component;

/**
 * @author Robin Engel
 */
@Component
public class UserWorkdayDefinitionConverter implements DtoConverter<WorkdayDefinitionDto, UserWorkdayDefinition> {

    @Override
    public WorkdayDefinitionDto toDto(UserWorkdayDefinition model) {
        WorkdayDefinitionDto dto = new WorkdayDefinitionDto();
        dto.setMonday(model.isMonday());
        dto.setTuesday(model.isTuesday());
        dto.setWednesday(model.isWednesday());
        dto.setThursday(model.isThursday());
        dto.setFriday(model.isFriday());
        dto.setSaturday(model.isSaturday());
        dto.setSunday(model.isSunday());
        return dto;
    }

    @Override
    public UserWorkdayDefinition toModel(WorkdayDefinitionDto dto) {
        UserWorkdayDefinition workdayDefinition = new UserWorkdayDefinition();
        workdayDefinition.setMonday(dto.isMonday());
        workdayDefinition.setTuesday(dto.isTuesday());
        workdayDefinition.setWednesday(dto.isWednesday());
        workdayDefinition.setThursday(dto.isThursday());
        workdayDefinition.setFriday(dto.isFriday());
        workdayDefinition.setSaturday(dto.isSaturday());
        workdayDefinition.setSunday(dto.isSunday());
        return workdayDefinition;
    }
}
