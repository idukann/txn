package com.validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Receiver {
    private static final Logger logger =
            LoggerFactory.getLogger(Receiver.class);

    public void handleMessage(HashMap<String,Object> mailMap){


            System.out.println("mail queue..................................." + mailMap);

    }
}