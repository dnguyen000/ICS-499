// the controller class has model view features, end-point config, and error handling
package edu.metrostate.ics499.team2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.metrostate.ics499.team2.model.Element;
import edu.metrostate.ics499.team2.repositories.ElementRepository;
import edu.metrostate.ics499.team2.services.ElementService;

@Controller
//@RestController
// thymeleaf doesn't work with @RestController
// https://stackoverflow.com/questions/63965406/unable-to-render-thymleaf-page-from-spring-boot-controller-prints-only-the-retu
@RequestMapping("/elements")
public class ElementController {
	
    private final ElementRepository elementRepo;
    private final ElementService elmService;
	
	@Autowired
	public ElementController(ElementService service, ElementRepository repository) {
		this.elmService = service;
		this.elementRepo = repository;
	}
	
    @GetMapping
    public String getElements(@RequestParam(value="family", required=false)String family, Model model) {
    	List<Element> elmFams = this.elmService.getElementByFamily(family);
    	model.addAttribute("elmFams", elmFams);
		return "elements";
    	
    }
	
//	Model View Controller (MVC)
//	@GetMapping
//	public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request) {
//		modelAndView.setViewName("index");
//		return modelAndView;
//	}
	
//    @GetMapping("/hello")
//    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
//    	return String.format("Hello %s!", name);
//    }
    
    @GetMapping("/all")
    public Iterable<Element> getElements(){
    	return this.elementRepo.findAll();
    }

}
