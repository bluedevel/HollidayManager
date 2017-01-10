package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nightcrawler on 02.01.2017.
 */
public interface UserDao extends CrudRepository<User, Long> {


}
