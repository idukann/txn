package com.validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Receiver {
    private static final Logger logger =
            LoggerFactory.getLogger(Receiver.class);
    validator objXml=new validator();
    public ArrayList<String> handleMessage(HashMap<String,Object> mailMap){
        ArrayList<String> errorlist=new ArrayList<String>();
        for (Map.Entry<String, Object> entry : mailMap.entrySet())
        {
           if(entry.getKey().startsWith("VALIDATOR"))
            {
                for (Map.Entry<String, Object> entry1 : mailMap.entrySet())
                {
                    if (("VALIDATOR"+entry1.getKey()).toUpperCase().equals(entry.getKey()))
                    {

                        errorlist= validator.validations(entry1.getValue().toString(),entry.getValue().toString(),entry1.getKey());
                        if (errorlist.size()==0)
                        {
                            System.out.println(entry1.getKey()+ " is fine");
                        }
                        else
                        {
                         return errorlist;
                        }

                    }
                }

            }
        }

return errorlist;


    }
}