package com.globallogic.rest.server;

import com.globallogic.rest.controller.HelloController;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;
@Service
public class HelloService {


    private static final Logger logger = getLogger(HelloController.class);
    public String getMessage(String name, boolean upperCase, boolean magic, String timezone) {
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
