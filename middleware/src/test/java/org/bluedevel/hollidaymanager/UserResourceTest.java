package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.resources.exceptions.UserAlreadyExistsException;
import org.bluedevel.hollidaymanager.resources.exceptions.UserNotFoundException;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(contentTypeJson))
                .andExpect(jsonPath("$.exception",
                        is(UserNotFoundException.class.getName())));
    }

    @Test
    public void addUser() throws Exception {
        perform(put("/users"), this.newUserFranz)
                .andExpect(status().isCreated());
    }

    @Test
    public void addUserAlreadyExists() throws Exception {
        perform(put("/users"), this.newUserHelga)
                .andExpect(status().isConflict())
                .andExpect(content().contentType(contentTypeJson))
                .andExpect(jsonPath("$.exception",
                        is(UserAlreadyExistsException.class.getName())));;
    }
}
