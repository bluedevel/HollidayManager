package org.bluedevel.hollidaymanager;

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
        this.mockMvc.perform(get("/users/" + this.userHelga.getUsername()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.username", is(this.userHelga.getUsername())));
    }

    @Test
    public void getUserNotFound() throws Exception {
        this.mockMvc.perform(get("/users/nonexistent"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addUser() throws Exception {
        this.mockMvc.perform(put("/users")
                .contentType(contentType)
                .content(json(this.newUserFranz)))
                .andExpect(status().isCreated());
    }

    @Test
    public void addUserAlreadyExists() throws Exception {
        this.mockMvc.perform(put("/users")
                .contentType(contentType)
                .content(json(this.newUserHelga)))
                .andExpect(status().isCreated());
    }
}
