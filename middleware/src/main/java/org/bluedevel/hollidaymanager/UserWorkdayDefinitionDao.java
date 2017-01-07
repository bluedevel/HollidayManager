package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.UserWorkdayDefinition;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nightcrawler on 03.01.2017.
 */
public interface UserWorkdayDefinitionDao extends CrudRepository<UserWorkdayDefinition, Long> {
}
