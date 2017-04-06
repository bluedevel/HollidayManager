package org.bluedevel.hollidaymanager.resources.dto;

/**
 * @author Robin Engel
 */
public class NewUserDto extends UserDto {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
