package com.validator;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class validator {

    public static  ArrayList<String> validations(String valuetobevalidated,String validation,String key) {

            List<String> validationList = Arrays.asList(validation.split(","));
            boolean flag=true;
            int index=0;
            ArrayList<String> errorlist=new ArrayList<String>();;
            for (String element : validationList) {

                if (element.equals("NOTNULL"))
                {
                    flag=isnulloremplty(valuetobevalidated);
                   if (!flag)
                   {
                       errorlist.add(index++,key+ " is null or emplty.Please check");
                   }
                }
                else if (element.equals("NUMERIC"))
                {
                    flag=numeric(valuetobevalidated);
                    if (!flag)
                    {
                        errorlist.add(index++,key+ " is not NUMERIC");
                    }
                }
                else if (element.equals("ALPHANUMERIC"))
                {
                    flag=alphanumeric(valuetobevalidated);
                    if (!flag)
                    {
                        errorlist.add(index++,key + " is not ALPHANUMERIC");
                    }
                }
                else if (element.equals("FLOATING"))
                {
                    flag=floating(valuetobevalidated);
                    if (!flag)
                    {
                        errorlist.add(index++,(key + " is not FLOATING"));
                }
            }

    }

        return  errorlist;
    }

    public static Boolean numeric(String valuetobevalidated)
    {
        boolean numeric = true;

        try {
            Double num = Double.parseDouble(valuetobevalidated);
        } catch (NumberFormatException e) {
            numeric = false;
        }

        if(numeric)
            return true;
        else
            return false;


    }

    public static Boolean floating(String valuetobevalidated)
    {
        if (valuetobevalidated.matches("[-+]?[0-9]*\\.?[0-9]+")) {
            return true;
        }

        return false;
    }

    public static Boolean isnulloremplty(String valuetobevalidated)
    {
        if(valuetobevalidated != null && !valuetobevalidated.isEmpty())
        {
            return true;
        }

        return false;
    }

    public static Boolean alphanumeric(String valuetobevalidated)
    {
        if(valuetobevalidated.matches("[a-zA-Z0-9]+"))
        {
            return true;
        }
        return false;
        }



}

