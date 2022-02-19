package edu.metrostate.ics499.team2.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import edu.metrostate.ics499.team2.controllers.ElementController;
import edu.metrostate.ics499.team2.model.Element;

@Service
public class ElementService {
	private final ElementController elmController;
	
	@Autowired
	public ElementService(ElementController elmController) {
		this.elmController = elmController;		
	}
	
	//	algorithm partially from url below
	//	https://www.linkedin.com/learning/learning-spring-with-spring-boot-2019/develop-a-service-object-with-spring
	public List<Element> getElementByFamily(String family) {
		Iterable<Element> elmsFams = elmController.showAll(family);
=======
import edu.metrostate.ics499.team2.model.Element;
import edu.metrostate.ics499.team2.repositories.ElementRepository;

@Service
public class ElementService implements ServiceInterface<Element> {
	private final ElementRepository elmRepo;
	
	@Autowired
	public ElementService(ElementRepository elmRepo) {
		this.elmRepo = elmRepo;		
	}
	
//	algorithm partially from url below
//	https://www.linkedin.com/learning/learning-spring-with-spring-boot-2019/develop-a-service-object-with-spring
	public List<Element> getElementByFamily(String family) {
		Iterable<Element> elmsFams = elmRepo.findElementByFamily(family);
>>>>>>> main
		Map<String, Element> elementsMap = new HashMap<String, Element>();
		elmsFams.forEach(element -> {
			elementsMap.put(element.getSymbol(), element);
		});
		List<Element> elms = new ArrayList<>();
		for(String symbol: elementsMap.keySet()) {
			elms.add(elementsMap.get(symbol));
		}
<<<<<<< HEAD
		// apparently this sorts by atomicNumber?
=======
		// apparently this sorts by atomicNumber
>>>>>>> main
		Collections.sort(elms);  
		return elms;	
	}
	
<<<<<<< HEAD
	// still mostly command line friendly
	// 2. Get item by symbol
	public void getElementBySymbol(String symbol) {
		System.out.println("Getting item by symbol: " + symbol);
		Element elm = elmController.findElementBySymbol(symbol);
		System.out.println(getItemDetails(elm));
	}
 
	// still mostly command line friendly
	// 3. Get name and symbol of a all items of a particular family
	public void getElementsByFamily(String family) {
		System.out.println("Getting items for the family " + family);
		List<Element> list = elmController.showAll(family);
		list.forEach(item -> System.out.println("Name: " + item.getName() + ", Symbol: " + item.getSymbol()));
	}
 
	// still mostly command line friendly
	// 4. Get count of documents in the collection
	public void findCountOfElements() {
		long count = elmController.count();
		System.out.println("Number of documents in the collection: " + count);
	}
 
	// still mostly command line friendly	
=======
	//CREATE
    // name, symbol, family, atomicNumber, massNumber, valence, ionicCharge
    public void createPeriodicElements() {
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
    }
    
	// READ
	// 1. Show all the data
	public List<Element> showAllElements() {
//		elmRepo.findAll().forEach(item -> System.out.println(getItemDetails(item)));
    	return elmRepo.findAll();
	}
	
	// 2. Get item by symbol
	public void getElementBySymbol(String symbol) {
		System.out.println("Getting item by symbol: " + symbol);
		Element elm = elmRepo.findElementBySymbol(symbol);
		System.out.println(getItemDetails(elm));
	}
 
	// 3. Get name and symbol of a all items of a particular family
	public void getElementsByFamily(String family) {
		System.out.println("Getting items for the family " + family);
		List<Element> list = elmRepo.findAll(family);
		list.forEach(item -> System.out.println("Name: " + item.getName() + ", Symbol: " + item.getSymbol()));
	}
 
	// 4. Get count of documents in the collection
	public void findCountOfElements() {
		long count = elmRepo.count();
		System.out.println("Number of documents in the collection: " + count);
	}
 
>>>>>>> main
	// Print details in readable form 
	public static String getItemDetails(Element item) {
//		System.out.println(
//		"Item Name: " + item.getName() + 
//		", \nSymbol: " + item.getSymbol() +
//		", \nItem Atomic Number: " + item.getAtomicNum() +
//		", \nItem Family: " + item.getFamily()
//		);         
//		return "";
		return
			"Item Name: " + item.getName() + 
			", \nSymbol: " + item.getSymbol() +
			", \nItem Atomic Number: " + item.getAtomicNumber() +
			", \nItem Family: " + item.getFamily()
		;
	}

<<<<<<< HEAD
=======
	@Override
	public boolean isValid(Element obj) {
		return showAllElements().stream()
				.filter(element -> element.equals(obj)).toList().size() > 0 ? true : false;
	}

>>>>>>> main
}
