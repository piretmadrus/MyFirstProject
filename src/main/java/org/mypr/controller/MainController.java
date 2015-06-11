package org.mypr.controller;

import java.util.List;

import org.mypr.model.Person;
import org.mypr.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@Controller
@RequestMapping("/app")
public class MainController {

	@Autowired
	DataService dataService;
	
	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = "/tere", method = RequestMethod.GET)
	public String sayHello() {
		return "hello";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String logIn() {
		log.info("LOGIN");
		return "login";
	}
	
	@RequestMapping(value = "/subjektid", method = RequestMethod.GET)
	public String getSubjekt(Model model) {
		log.info("Controller called");
		Person person = null;
		List<Person> persons = null;

		try{
			person = dataService.getPersonById(1);
			persons = dataService.findAll();
		}
		catch (Exception ex){
			System.out.println("TreeServiceImpl.getTrees():"+ex.getMessage());
		}
		model.addAttribute("person",person);
		model.addAttribute("persons", persons);
		model.addAttribute("title", "Hello world!");
		return "intro";
	}
	
	@RequestMapping(value="/data", method=RequestMethod.GET)
	public @ResponseBody Person getData(){

		Person person = null;

		try{
			person = dataService.getPersonById(1);
		}
		catch (Exception ex){
			System.out.println("DefaultController.get_andmed():"+ex.getMessage());
		}
		return person;
	}
}
