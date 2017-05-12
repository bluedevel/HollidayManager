package org.bluedevel.hollidaymanager.resources;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Robin Engel
 */
public class UserResourceTest extends BaseTest {

    @Test
    public void getUser() throws Exception {
        perform(get("/users/" + this.userHelga.getUsername()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(this.userHelga.getUsername())));
    }

    @Test
    public void getUserNotFound() throws Exception {
        perform(get("/users/nonexistent"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addUser() throws Exception {
        perform(put("/users"), this.newUserFranz)
                .andExpect(status().isCreated());
    }

    @Test
    public void addUserAlreadyExists() throws Exception {
        perform(put("/users"), this.newUserHelga)
                .andExpect(status().isConflict());
    }
}
