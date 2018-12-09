package com.selector.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.selector.model.Employee;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/selector-rabbitmq/" ,method = RequestMethod.POST)
public class RequestHandlerController {
    private final RabbitTemplate rabbitTemplate;
    public RequestHandlerController(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

     XmlReader objXml=new XmlReader();
    @GetMapping(value = "/NewRequest")
    public String NewRequest(@RequestBody Map<String, Object> payload) throws JsonProcessingException {

        rabbitTemplate.convertAndSend("validator.exchange","validator.routingkey",payload);

        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }

}
