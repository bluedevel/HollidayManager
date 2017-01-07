package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.Department;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nightcrawler on 03.01.2017.
 */
public interface DepartmentDao extends CrudRepository<Department, Long> {
}
