package org.bluedevel.hollidaymanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Robin Engel
 */
@Controller
public class CalendarController {

    @RequestMapping("/calendar")
    public String calendar(Model model) {
        return "calendar";
    }

}
