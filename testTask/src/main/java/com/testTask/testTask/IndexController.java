package com.testTask.testTask;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {
    @GetMapping("/h")
    public String index() {
        return "hello";
//        Map<String, String> model = new HashMap<>();
//        model.put("name", "Alexey");
        //return new ModelAndView("index", model);
    }
}
