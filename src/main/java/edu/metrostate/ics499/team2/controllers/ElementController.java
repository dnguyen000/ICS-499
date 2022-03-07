// the controller class has model view features, end-point config, and error handling
package edu.metrostate.ics499.team2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import edu.metrostate.ics499.team2.model.Element;
import edu.metrostate.ics499.team2.services.ElementService;

// thymeleaf doesn't work with @RestController (see url below)
// https://stackoverflow.com/questions/63965406/unable-to-render-thymleaf-page-from-spring-boot-controller-prints-only-the-retu
// @RestController
@Controller
@RequestMapping("/elements")
public class ElementController {
	
    private final ElementService elmService;
	
	@Autowired
	public ElementController(ElementService service) {
		this.elmService = service;
	}
	
	// thymeleaf table template of all elements
    @GetMapping
    public String getElements(Model model) {
    	model.addAttribute("elms", list());
		return "elements";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Element> list() {
    	return elmService.listAll();
    }

    @GetMapping("/symbol/{symbol}")
    @ResponseBody
    public Element findElementBySymbol(@PathVariable("symbol") String symbol) {
    	return elmService.getElementBySymbol(symbol);
    }

}
