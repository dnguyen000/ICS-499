// the controller class has model view features, end-point config, and error handling
package edu.metrostate.ics499.team2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import edu.metrostate.ics499.team2.model.Element;
import edu.metrostate.ics499.team2.repositories.ElementRepository;
import edu.metrostate.ics499.team2.services.ElementService;

// thymeleaf doesn't work with @RestController (see url below)
// https://stackoverflow.com/questions/63965406/unable-to-render-thymleaf-page-from-spring-boot-controller-prints-only-the-retu
// @RestController
@Controller
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
    	List<Element> elms;
    	if(null != family)
    		elms = this.elmService.getElementByFamily(family);
    	else
    		elms = this.elementRepo.findAll();
    	
    	model.addAttribute("elmFams", elms);
		return "elements";
    }
	
//  just storing this because I don't understand it
//	Model View Controller (MVC)
//	@GetMapping
//	public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request) {
//		modelAndView.setViewName("index");
//		return modelAndView;
//	}

}
