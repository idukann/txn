package com.roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Receiver {
    private static final Logger logger =
            LoggerFactory.getLogger(Receiver.class);
    RolesValidator objXml=new RolesValidator();
    public void handleMessage(HashMap<String,Object> mailMap){


            if ("YES".equals(mailMap.get("ISROLEAPPLICABLE")))
            {
                objXml.rolesvalidation();
            }

    }
}