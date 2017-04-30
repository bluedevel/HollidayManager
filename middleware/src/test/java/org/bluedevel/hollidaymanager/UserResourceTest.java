package org.bluedevel.hollidaymanager;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Robin Engel
 */
public class UserResourceTest extends BaseTest {

    @Test
    public void getUser() throws Exception {
        this.mockMvc.perform(put("/users/" + this.userHelga.getUsername()))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserNotFound() throws Exception {
        this.mockMvc.perform(put("/users/nonexistent"))
                .andExpect(status().isNotFound());
    }
}
