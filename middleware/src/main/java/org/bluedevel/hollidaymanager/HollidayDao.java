package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.Holliday;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nightcrawler on 20.12.2016.
 */

public interface HollidayDao extends CrudRepository<Holliday,Long>{

}
