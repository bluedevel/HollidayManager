package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.WorkdayDefinition;
import org.springframework.stereotype.Component;

/**
 * @author Robin Engel
 */
@Component
public class WorkdayDefinitionService {

    public WorkdayDefinition getEffectiveWorkdayDefinition(WorkdayDefinition... workdayDefinitions) {
        return workdayDefinitions[workdayDefinitions.length - 1];
    }
}
