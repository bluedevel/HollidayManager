package org.bluedevel.hollidaymanager;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Robin Engel
 */
public class DepartmentResourceTest extends BaseTest {

    @Test
    public void getDepartment() throws Exception {
        perform(get("/departments/" + this.departmentCats.getName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(this.departmentCats.getName())));
    }

    @Test
    public void getDepartmentNotFound() throws Exception {
        perform(get("/departments/dragons"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addDepartment() throws Exception {
        perform(put("/departments"), this.newDepartmentDogs)
                .andExpect(status().isCreated());
    }

    @Test
    public void addDepartmentAlreadyExists() throws Exception {
        perform((put("/departments")), this.newDepartmentCats)
                .andExpect(status().isConflict());
    }
}
