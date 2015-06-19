package org.mypr.controller;

import java.util.List;

import org.mypr.model.Address;
import org.mypr.model.Person;
import org.mypr.service.AddressService;
import org.mypr.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/app")
public class MainController {

	@Autowired
	DataService dataService;

	@Autowired
	AddressService addressService;

	private static final Logger log = LoggerFactory
			.getLogger(MainController.class);

	@RequestMapping(value = "/tere", method = RequestMethod.GET)
	public String sayHello() {
		return "hello";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String logIn() {
		log.info("LOGIN");
		return "login";
	}

	@RequestMapping(value = "/firstpage", method = RequestMethod.GET)
	public String firstPage(Model model) {
		log.info("FIRST PAGE");
		model.addAttribute("message", "Tere tulemast esimesele lehele!");
		return "message";
	}

	/**
	 * size=20 1: 0-19 2: 20-39 3: 40-59
	 * 
	 * page = n / size pageindex = n % size
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public String pageRedirect(Model model) {
		return "redirect:/app/page/1";
		/*Page<Address> addresses = addressService.findAll(new PageRequest(2, 5));
		List<Address> addressList = addresses.getContent();

		List<Address> orderedAddresses = addressService
				.findByCountryOrderByTownVillageAsc("Eesti");

		model.addAttribute("addressList", addressList);
		model.addAttribute("orderedAddresses", orderedAddresses);
		return "page";*/
	}

	@RequestMapping(value = "/page/{pageNumber}", method = RequestMethod.GET)
	public String pagination(@PathVariable Integer pageNumber, Model model) {
		//esimene nr näitab mitmes lehekülg, teine, mitu asja on ühel lehel
		PageRequest newPgb = new PageRequest(pageNumber - 1, 5);
		Page<Address> currentResults = addressService.findAll(newPgb);
		model.addAttribute("currentResults", currentResults);
		//List<Address> addressList = addresses.getContent();

		//Pagination variables
		//getNumber() Returns the number of the current Slice.
        int current = currentResults.getNumber() + 1;
        //need begin ja end on vajalikud siis, praegusel juhul esitatakse 11 lehekülge ja saab kerida nii edasi kui tagasi
        //kui esimene on nt nr 5, siis viimane on 5+10
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, currentResults.getTotalPages()); // how many pages to display in the pagination bar
        int totalPages = currentResults.getTotalPages();
        
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPages", totalPages);
		/*List<Address> orderedAddresses = addressService
				.findByCountryOrderByTownVillageAsc("Eesti");

		model.addAttribute("addressList", addressList);
		model.addAttribute("orderedAddresses", orderedAddresses);*/
		return "page";
	}

	@RequestMapping(value = "/subjektid", method = RequestMethod.GET)
	public String getSubjekt(Model model) {
		log.info("Controller called");
		Person person = null;
		List<Person> persons = null;
		Long countyCount = 0L;

		try {
			person = dataService.getPersonById(1);
			persons = dataService.findAll();
			countyCount = addressService.countByCounty("Harju");
		} catch (Exception ex) {
			System.out.println("TreeServiceImpl.getTrees():" + ex.getMessage());
		}
		model.addAttribute("person", person);
		model.addAttribute("persons", persons);
		model.addAttribute("title", "Hello World");
		model.addAttribute("count", countyCount);
		return "intro";
	}

	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public @ResponseBody Person getData() {

		Person person = null;

		try {
			person = dataService.getPersonById(1);
		} catch (Exception ex) {
			System.out.println("DefaultController.get_andmed():"
					+ ex.getMessage());
		}
		return person;
	}
}
