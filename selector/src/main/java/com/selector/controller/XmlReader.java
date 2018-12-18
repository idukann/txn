package com.selector.controller;

import java.io.File;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlReader {

    public Map<String, Object> parser(Map<String, Object> payload) {
        try {

            File stocks = new File("src/main/java/com/selector/controller/services/" + payload.get("service") + ".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stocks);
            doc.getDocumentElement().normalize();

            NodeList flowList = doc.getElementsByTagName("MICROSERVICEVALIDATOR");

            for (int i = 0; i < flowList.getLength(); i++) {

                NodeList childList = flowList.item(0).getChildNodes();
                for (int j = 0; j < childList.getLength(); j++) {
                    Node childNode = childList.item(j);
                    if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                        payload.put(childNode.getNodeName() ,childList.item(j).getTextContent().trim());
                    }


                }
            }

            NodeList flowList1 = doc.getElementsByTagName("MICROSERVICEROLES");

            for (int i = 0; i < flowList1.getLength(); i++) {

                NodeList childList1 = flowList1.item(0).getChildNodes();
                for (int j = 0; j < childList1.getLength(); j++) {
                    Node childNode = childList1.item(j);
                    if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                        payload.put(childNode.getNodeName() ,childList1.item(j).getTextContent().trim());
                    }


                }
            }

    }

        catch (Exception ex) {
            System.out.println("File does not exist");
        }
        return  payload;
    }

}

