package com.globallogic.rest.controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class HelloController {
    
    private static final Logger logger = getLogger(HelloController.class);


    @GetMapping("hello/{userName}")
    public String hello(@PathVariable(name = "userName") String name,
                        @RequestParam(required = false) boolean upperCase,
                        @RequestParam(required = false) boolean magic,
                        @RequestHeader(name = "Timezone", required = false) String timezone) {
        String result = name;
        if (upperCase) {
            result = result.toUpperCase();
        }
        if (magic) {
            result = "✧･ﾟ: *✧･ﾟ:*  *:･ﾟ✧*:･ﾟ✧" + result + "✧･ﾟ: *✧･ﾟ:*  *:･ﾟ✧*:･ﾟ✧";
        }
        logger.info("Timezone: " + timezone);
        return "Hello " + result;
    }


}
