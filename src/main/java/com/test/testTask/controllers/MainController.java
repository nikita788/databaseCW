package com.test.testTask.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {

    @RequestMapping(value = "/")
    public ModelAndView getPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("../static/indexx");
        return model;
    }

}
