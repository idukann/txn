package com.validator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@SpringBootApplication
public class ValidatorApplication {

	@Bean
	SimpleMessageListenerContainer container(
			ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container =
				new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames("validator.queue");
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		final Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();


		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		converter.setJsonObjectMapper(mapper);
		return converter;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver,MessageConverter jsonMessageConverter) {
		System.out.print(receiver);
		return new MessageListenerAdapter(receiver,jsonMessageConverter);
	}

	public static void main(String[] args) {

		SpringApplication.run(
				new Object[] { ValidatorApplication.class }, args);
	}



	}


