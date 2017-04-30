package org.bluedevel.hollidaymanager.daos;

import org.bluedevel.hollidaymanager.models.Holiday;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nightcrawler on 20.12.2016.
 */

public interface HolidayDao extends CrudRepository<Holiday, Long> {



}
