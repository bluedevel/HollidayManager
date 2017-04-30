package org.bluedevel.hollidaymanager.daos;

import org.bluedevel.hollidaymanager.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Nightcrawler on 02.01.2017.
 */
public interface UserDao extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
