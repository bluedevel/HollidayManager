package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.Department;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Robin Engel on 04.05.17.
 */
public class DepartmentResourceTest extends BaseTest {

    @Test
    public void getDepartment() throws Exception {
        perform(get("/departments/" + departmentCats.getName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int) departmentCats.getId())))
                .andExpect(jsonPath("$.name", is(departmentCats.getName())));
    }

    @Test
    public void getDepartmentNotFound() throws Exception {
        perform(get("/departments/dinosaurs"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addDepartment() throws Exception {

        perform(put("/departments"), departmentDogs)
                .andExpect(status().isOk());

        Optional<Department> department = departmentDao.findByName(departmentDogs.getName());
        if (department.isPresent()) {
            assertThat(department.get().getName(), is(departmentDogs.getName()));
        } else {
            fail();
        }
    }

    @Test
    public void addDepartmentAlreadyExists() throws Exception {
        perform(put("/departments"), new Department("Cats"))
                .andExpect(status().isConflict());
    }
}
