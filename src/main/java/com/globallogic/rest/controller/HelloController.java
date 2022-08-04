package com.globallogic.rest.controller;

import com.globallogic.rest.server.HelloService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("hello/{userName}")
    public String hello(@PathVariable(name = "userName") String name,
                        @RequestParam(required = false) boolean upperCase,
                        @RequestParam(required = false) boolean magic,
                        @RequestHeader(name = "Timezone", required = false) String timezone) {
        return helloService.getMessage(name, upperCase, magic, timezone);
    }




}
