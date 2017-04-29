package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Nightcrawler on 03.01.2017.
 */
public interface DepartmentDao extends CrudRepository<Department, Long> {

    Optional<Department> findByName(String name);

}
