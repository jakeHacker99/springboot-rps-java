package rps.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rps.app.utilities.DefaultBody;

@RestController
public class TestController {

    @GetMapping(value = "/test")
    public DefaultBody test() {
        return new DefaultBody("Hi", "testing");
    }

}
