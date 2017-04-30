package org.bluedevel.hollidaymanager.daos;

import org.bluedevel.hollidaymanager.models.GlobalWorkdayDefinition;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nightcrawler on 03.01.2017.
 */
public interface GlobalWorkdayDefinitionDao extends CrudRepository<GlobalWorkdayDefinition, Long> {
}
