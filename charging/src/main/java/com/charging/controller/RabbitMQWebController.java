package com.charging.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.charging.model.Employee;

@RestController
@RequestMapping(value = "/charging-rabbitmq/")
public class RabbitMQWebController {


	@GetMapping(value = "/producer")
	public String producer(@RequestParam("empName") String empName,@RequestParam("empId") String empId) {
	
	Employee emp=new Employee();
	emp.setEmpId(empId);
	emp.setEmpName(empName);


		return "Message sent to the RabbitMQ JavaInUse Successfully";
	}

}

