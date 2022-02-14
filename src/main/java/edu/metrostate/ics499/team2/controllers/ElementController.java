// the controller class has model view features, end-point config, and error handling
package edu.metrostate.ics499.team2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
	
	// provides a thymeleaf table template of all elements in ascending order, or by family with GET variable family
    @GetMapping
    public String getElements(@RequestParam(value="family", required=false)String family, Model model) {
    	List<Element> elms;
    	if(null != family)
    		elms = this.elmService.getElementByFamily(family);
    	else
    		elms = this.elementRepo.findAll(Sort.by(Sort.Direction.ASC, "atomicNumber"));
    	
    	model.addAttribute("elms", elms);
		return "elements";
    }
	
//  just storing this because I don't understand it
//	Model View Controller (MVC)
//	@GetMapping
//	public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request) {
//		modelAndView.setViewName("index");
//		return modelAndView;
//	}
    
    @GetMapping("/createall")
    @ResponseBody
    public String createAll() {
//		System.out.println("-------------CREATE ELEMENTS -------------------------------\n");
    	elmService.createPeriodicElements();
    	return "Data creation complete...";
    }
    
    @GetMapping("/showall")
    @ResponseBody
    public List<Element> showAll() {
//    	System.out.println("\n----------------SHOW ALL ELEMENTS ---------------------------\n");
    	return elmService.showAllElements();
    }
    
//	public void run(String ...args) {
//		System.out.println("\n--------------GET ELEMENTS BY SYMBOL-----------------------------------\n");
//		elmService.getElementBySymbol("He");
//		System.out.println("\n-----------GET ELEMENTS BY FAMILY ---------------------------------\n");
//		elmService.getElementsByFamily("Metal");
//		// System.out.println("\n-----------UPDATE CATEGORY NAME OF SNACKS CATEGORY----------------\n");
//		// updateCategoryName("snacks");
//		// System.out.println("\n----------DELETE A GROCERY ITEM----------------------------------\n");
//		// deleteGroceryItem("Kodo Millet"); 
//		System.out.println("\n------------FINAL COUNT OF ELEMENTS -------------------------\n");
//		elmService.findCountOfElements();
//		System.out.println("\n-------------------THANK YOU---------------------------");
//	}

}
