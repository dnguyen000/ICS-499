// the controller class has model view features, end-point config, and error handling
package edu.metrostate.ics499.team2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.metrostate.ics499.team2.ChemistryApplication;
import edu.metrostate.ics499.team2.model.Element;
import edu.metrostate.ics499.team2.repositories.ElementRepository;

@RestController
public class ElementController {
	
    private ElementRepository ElementRepo;
	
	@Autowired
	public ElementController(ElementRepository repository) {
		this.ElementRepo = repository;
	}
	
//	Model View Controller (MVC)
//	@GetMapping
//	public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request) {
//		modelAndView.setViewName("index");
//		return modelAndView;
//	}
	
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    	return String.format("Hello %s!", name);
    }
    
    @GetMapping("/element/{symbol}")
    public String element(@PathVariable String symbol) {
        Element elm = ElementRepo.findItemBySymbol(symbol);
    	return ChemistryApplication.getItemDetails(elm);
    }

}
