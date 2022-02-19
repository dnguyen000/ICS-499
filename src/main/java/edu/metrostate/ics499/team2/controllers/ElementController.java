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
	
<<<<<<< HEAD
    private final ElementRepository elmRepo;
	
	@Autowired
	public ElementController(ElementRepository elmRepo) {
		this.elmRepo = elmRepo;
=======
    private final ElementService elmService;
	
	@Autowired
	public ElementController(ElementService service) {
		this.elmService = service;
>>>>>>> main
	}
	
	// provides a thymeleaf table template of all elements in ascending order, or by family with GET variable family
    @GetMapping
    public String getElements(@RequestParam(value="family", required=false)String family, Model model) {
    	List<Element> elms;
    	if(null != family)
<<<<<<< HEAD
    		elms = elmRepo.findAll(family);
    	else
    		elms = elmRepo.findAll();
=======
    		elms = this.elmService.getElementByFamily(family);
    	else
    		elms = this.elmService.showAllElements();
>>>>>>> main
    	
    	model.addAttribute("elms", elms);
		return "elements";
    }
<<<<<<< HEAD
    

	// CREATE
    @GetMapping("/createall")
    @ResponseBody
    public void createPeriodicElements() {
        // name, symbol, family, atomicNumber, massNumber, valence, ionicCharge
    	System.out.println("Data creation started...");
        // period 1
        elmRepo.save(new Element("Hydrogen", 		"H", 	"Nonmetal", 	1, 1.008, 	0, 0));
        elmRepo.save(new Element("Helium", 			"He", 	"Nonmetal", 	2, 4.003, 	0, 0));
        // period 2
        elmRepo.save(new Element("Lithium", 		"Li", 	"Metal", 		3, 6.941, 	0, 0));
        elmRepo.save(new Element("Beryllium", 		"Be", 	"Metal", 		4, 9.012, 	0, 0));
        elmRepo.save(new Element("Boron", 			"B", 	"Metalloid", 	5, 10.81, 	0, 0));
        elmRepo.save(new Element("Carbon", 			"C", 	"Nonmetal", 	6, 12.01, 	0, 0));
        elmRepo.save(new Element("Nitrogen", 		"N", 	"Nonmetal", 	7, 14.01, 	0, 0));
        elmRepo.save(new Element("Oxygen", 			"O", 	"Nonmetal", 	8, 16, 		0, 0));
        elmRepo.save(new Element("Flourine", 		"F", 	"Nonmetal", 	9, 19, 		0, 0));
        elmRepo.save(new Element("Neon", 			"Ne", 	"Nonmetal", 	10, 20.18, 	0, 0));
        // period 3
        elmRepo.save(new Element("Sodium", 			"Na", 	"Metal", 		11, 22.99,	0, 0));
        elmRepo.save(new Element("Magnesium", 		"Mg", 	"Metal", 		12, 24.31,	0, 0));
        elmRepo.save(new Element("Aluminium", 		"Al", 	"Metal", 		13, 26.98,	0, 0));
        elmRepo.save(new Element("Silicon", 		"Si", 	"Metal", 		14, 28.09,	0, 0));
        elmRepo.save(new Element("Phosphorus", 		"P", 	"Nonmetal", 	15, 30.97,	0, 0));
        elmRepo.save(new Element("Sulfur", 			"S", 	"Nonmetal", 	16, 32.07,	0, 0));
        elmRepo.save(new Element("Chlorine", 		"Cl", 	"Nonmetal", 	17, 35.45,	0, 0));
        elmRepo.save(new Element("Argon", 			"Ar", 	"Nonmetal", 	18, 39.95,	0, 0));
        // period 4
        elmRepo.save(new Element("Potassium", 		"K", 	"Metal", 		19, 39.10,	0, 0));
        elmRepo.save(new Element("Calcium", 		"Ca", 	"Metal", 		20, 40.08,	0, 0));
        elmRepo.save(new Element("Scandium",		"Sc", 	"Metal", 		21, 44.96,	0, 0));
        elmRepo.save(new Element("Titanium", 		"Ti", 	"Metal", 		22, 57.88,	0, 0));
        elmRepo.save(new Element("Vanadium",		"V", 	"Metal", 		23, 50.94,	0, 0));
        elmRepo.save(new Element("Chromium",		"Cr", 	"Metal", 		24, 52, 	0, 0));
        elmRepo.save(new Element("Manganese",		"Mn", 	"Metal", 		25, 54.94,	0, 0));
        elmRepo.save(new Element("Iron", 			"Fe", 	"Metal", 		26, 55.85,	0, 0));
		elmRepo.save(new Element("Cobolt", 			"Co", 	"Metal", 		27, 58.93,	0, 0));
		elmRepo.save(new Element("Nickel", 			"Ni", 	"Metal", 		28, 58.69,	0, 0));
		elmRepo.save(new Element("Copper", 			"Cu", 	"Metal", 		29, 63.55,	0, 0));
		elmRepo.save(new Element("Zinc", 			"Zn", 	"Metal", 		30, 65.41,	0, 0));
		elmRepo.save(new Element("Gallium",			"Ga", 	"Metal", 		31, 69.72,	0, 0));
		elmRepo.save(new Element("Germanium",		"Ge", 	"Metalloid", 	32, 72.64, 	0, 0));
		elmRepo.save(new Element("Arsenic",			"As", 	"Metalloid", 	33, 74.92, 	0, 0));
		elmRepo.save(new Element("Selenium",		"Se", 	"Nonmetal", 	34, 78.96,	0, 0));
		elmRepo.save(new Element("Bromine",			"Br", 	"Nonmetal", 	35, 79.90,	0, 0));
		elmRepo.save(new Element("Krypton",			"Kr", 	"Nonmetal", 	36, 83.80,	0, 0));
		// period 5
		elmRepo.save(new Element("Rubidium",		"Rb", 	"Metal", 		37, 85.47, 0, 0));
		elmRepo.save(new Element("Strontium",		"Sr", 	"Metal", 		38, 87.62, 0, 0));
		elmRepo.save(new Element("Yttrium",			"Y", 	"Metal", 		39, 88.91, 0, 0));
		elmRepo.save(new Element("Zirconium",		"Zr", 	"Metal", 		40, 91.22, 0, 0));
		elmRepo.save(new Element("Niobium",			"Nb", 	"Metal", 		41, 92.91, 0, 0));
		elmRepo.save(new Element("Molybdenum",		"Mo", 	"Metal", 		42, 95.94, 0, 0));
		elmRepo.save(new Element("Technetium", 		"Tc", 	"Metal", 		43, (98), 0, 0));
		elmRepo.save(new Element("Ruthenium",		"Ru", 	"Metal", 		44, 101.1, 0, 0));
		elmRepo.save(new Element("Rhodium",			"Rh", 	"Metal", 		45, 102.9, 0, 0));
		elmRepo.save(new Element("Palladium",		"Pd", 	"Metal", 		46, 106.4, 0, 0));
		elmRepo.save(new Element("Silver",			"Ag", 	"Metal", 		47, 107.9, 0, 0));
		elmRepo.save(new Element("Cadmium",			"Cd", 	"Metal", 		48, 112.4, 0, 0));
		elmRepo.save(new Element("Indium", 			"In", 	"Metal", 		49, 114.8, 0, 0));
		elmRepo.save(new Element("Tin", 			"Sn", 	"Metal", 		50, 118.7, 0, 0));
		elmRepo.save(new Element("Antimony",		"Sb", 	"Metalloid", 	51, 121.8, 0, 0));
		elmRepo.save(new Element("Tellurium",		"Te", 	"Metalloid", 	52, 127.6, 0, 0));
		elmRepo.save(new Element("Iodine", 			"I", 	"Nonmetal", 	53, 126.9, 0, 0));
		elmRepo.save(new Element("Xenon", 			"Xe", 	"Nonmetal", 	54, 131.3, 0, 0));
		// period 6
		elmRepo.save(new Element("Casium", 			"Cs", 	"Metal", 		55, 132.9, 0, 0));
		elmRepo.save(new Element("Barium", 			"Ba", 	"Metal", 		56, 137.3, 0, 0));
		elmRepo.save(new Element("Lanthanum",		"La", 	"Metal", 		57, 138.9, 0, 0));

		elmRepo.save(new Element("Hafnium", 		"Hf", 	"Metal", 		72, 178.5, 0, 0));
		elmRepo.save(new Element("Tantalum",		"Ta", 	"Metal", 		73, 180.9, 0, 0));
		elmRepo.save(new Element("Tungsten",		"W",	"Metal", 		74, 183.8, 0, 0));
		elmRepo.save(new Element("Rhenium",			"Re", 	"Metal", 		75, 186.2, 0, 0));
		elmRepo.save(new Element("Osmium", 			"Os", 	"Metal", 		76, 190.2, 0, 0));
		elmRepo.save(new Element("Iridium", 		"Ir", 	"Metal", 		77, 192.2, 0, 0));
		elmRepo.save(new Element("Platinum",		"Pt", 	"Metal", 		78, 195.1, 0, 0));
		elmRepo.save(new Element("Gold", 			"Au", 	"Metal", 		79, 197.0, 0, 0));
		elmRepo.save(new Element("Mercury",			"Hg", 	"Metal", 		80, 200.6, 0, 0));
		elmRepo.save(new Element("Thallium",		"Tl", 	"Metal", 		81, 204.4, 0, 0));
		elmRepo.save(new Element("Lead", 			"Pb", 	"Metal", 		82, 207.2, 0, 0));
		elmRepo.save(new Element("Bismuth",			"Bi", 	"Metal", 		83, 209.0, 0, 0));
		elmRepo.save(new Element("Polonium", 		"Po", 	"Metalloid", 	84, (209), 0, 0));
		elmRepo.save(new Element("Astatine", 		"At", 	"Metalloid", 	85, (210), 0, 0));
		elmRepo.save(new Element("Radon", 			"Rn", 	"Nonmetal", 	86, (222), 0, 0));
		// period 7
		elmRepo.save(new Element("Francium",		"Fr", 	"Metal", 		87, (223), 0, 0));
		elmRepo.save(new Element("Radium", 			"Ra", 	"Metal", 		88, (226), 0, 0));
		elmRepo.save(new Element("Actinium",		"Ac", 	"Metal", 		89, (227), 0, 0));

		elmRepo.save(new Element("Rutherfordium",	"Rf", 	"Metal", 		104, (267), 0, 0));
		elmRepo.save(new Element("Dubnium", 		"Db", 	"Metal", 		105, (268), 0, 0));
		elmRepo.save(new Element("Seaborgium", 		"Sg", 	"Metal", 		106, (271), 0, 0));
		elmRepo.save(new Element("Bohrium", 		"Bh", 	"Metal", 		107, (272), 0, 0));
		elmRepo.save(new Element("Hassium", 		"Hs", 	"Metal", 		108, (270), 0, 0));
		elmRepo.save(new Element("Meitnerium", 		"Mt", 	"Metal", 		109, (276), 0, 0));
		elmRepo.save(new Element("Darmstadtium", 	"Ds",	"Metal", 		110, (281), 0, 0));
		elmRepo.save(new Element("Roentgenium",		"Rg", 	"Metal", 		111, (280), 0, 0));
		elmRepo.save(new Element("Copernicium",		"Cn", 	"Metal", 		112, (285), 0, 0));
		elmRepo.save(new Element("unknown", 		"Nh",  	"Metal", 		113, (284), 0, 0));
		elmRepo.save(new Element("unknown", 		"Fl", 	"Metal", 		114, (289), 0, 0));
		elmRepo.save(new Element("unknown", 		"Mc",  	"Metal", 		115, (289), 0, 0));
		elmRepo.save(new Element("unknwon", 		"Lv", 	"Metal", 		116, (293), 0, 0));
		elmRepo.save(new Element("unknown", 		"Ts", 	"Metal", 		117, (294), 0, 0));
		elmRepo.save(new Element("unknown", 		"Og", 	"Nonmetal", 	118, (294), 0, 0));
        System.out.println("Data creation complete...");
=======
	
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
>>>>>>> main
    }
    
    @GetMapping("/showall")
    @ResponseBody
    public List<Element> showAll() {
<<<<<<< HEAD
    	return elmRepo.findAll();
    }
    
    @GetMapping("/family")
    @ResponseBody
    public List<Element> showAll(String family) {
    	return elmRepo.findAll(family);
    }
    
    @GetMapping("/symbol")
    @ResponseBody
    public Element findElementBySymbol(String symbol) {
    	return elmRepo.findElementBySymbol(symbol);
    }
    
    public long count() {
    	return elmRepo.count();
    }
    
//	public void run(String ...args) {
=======
//    	System.out.println("\n----------------SHOW ALL ELEMENTS ---------------------------\n");
    	return elmService.showAllElements();
    }
    
//	public void run(String ...args) {
//		System.out.println("\n--------------GET ELEMENTS BY SYMBOL-----------------------------------\n");
//		elmService.getElementBySymbol("He");
//		System.out.println("\n-----------GET ELEMENTS BY FAMILY ---------------------------------\n");
//		elmService.getElementsByFamily("Metal");
>>>>>>> main
//		// System.out.println("\n-----------UPDATE CATEGORY NAME OF SNACKS CATEGORY----------------\n");
//		// updateCategoryName("snacks");
//		// System.out.println("\n----------DELETE A GROCERY ITEM----------------------------------\n");
//		// deleteGroceryItem("Kodo Millet"); 
//		System.out.println("\n------------FINAL COUNT OF ELEMENTS -------------------------\n");
//		elmService.findCountOfElements();
//		System.out.println("\n-------------------THANK YOU---------------------------");
//	}

}
