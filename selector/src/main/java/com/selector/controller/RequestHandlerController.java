package com.selector.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.selector.model.Employee;
import org.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping(value = "/selector-rabbitmq/" ,method = RequestMethod.POST)
public class RequestHandlerController {
    private final RabbitTemplate rabbitTemplate;
    public RequestHandlerController(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    ArrayList<String> errorlist=new ArrayList<String>();
     XmlReader objXml=new XmlReader();
    @GetMapping(value = "/NewRequest")
    public String NewRequest(@RequestBody Map<String, Object> payload) throws JsonProcessingException {
        payload = objXml.parser(payload);
        errorlist = (ArrayList<String>) rabbitTemplate.convertSendAndReceive("validator.exchange", "validator.routingkey", payload);
        if(errorlist.size()==0) {
            errorlist = (ArrayList<String>) rabbitTemplate.convertSendAndReceive("roles.exchange", "roles.routingkey", payload);
        }
        if(errorlist.size()==0) {
            errorlist = (ArrayList<String>) rabbitTemplate.convertSendAndReceive("validator.exchange", "validator.routingkey", payload);
        }

String TransactionStatus="Successful";
String message="";
        String message1="";
        if (errorlist.size() > 0)
        {
            TransactionStatus="FAILED";
            for (int i=0;i<errorlist.size();i++)
            {
                message=message+","+errorlist.get(i);

            }

        }

        JSONObject json = new JSONObject();
        json.put("SERVICE",payload.get("service"));
        json.put("TransactionID",payload.get("service"));
        json.put("TransactionStatus",TransactionStatus);
        json.put("message",message);
        return json.toString();
    }

}
