package edu.metrostate.ics499.team2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import edu.metrostate.ics499.team2.model.Element;
import edu.metrostate.ics499.team2.repositories.ElementRepository;

@SpringBootApplication
@EntityScan( basePackages = {"edu.metrostate.ics499.team2.model"} )
public class ChemistryApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChemistryApplication.class, args);
	}
	
	@Autowired
    ElementRepository ElementRepo;
	
    //CREATE
    // name, symbol, family, atomicNumber, massNumber, valence, ionicCharge
    void createPeriodicElements() {
        System.out.println("Data creation started...");
        // period 1
        ElementRepo.save(new Element("Hydrogen", "H", "Nonmetal", 1, 0, 0, 0));
        ElementRepo.save(new Element("Helium", "He", "Nonmetal", 2, 0, 0, 0));
        // period 2
        ElementRepo.save(new Element("Lithium", "Li", "Metal", 3, 0, 0, 0));
        ElementRepo.save(new Element("Beryllium", "Be", "Metal", 4, 0, 0, 0));
        ElementRepo.save(new Element("Boron", "B", "Metalloid", 5, 0, 0, 0));
        ElementRepo.save(new Element("Carbon", "C", "Nonmetal", 6, 0, 0, 0));
        ElementRepo.save(new Element("Nitrogen", "N", "Nonmetal", 7, 0, 0, 0));
        ElementRepo.save(new Element("Oxygen", "O", "Nonmetal", 8, 0, 0, 0));
        ElementRepo.save(new Element("...", "F", "Nonmetal", 9, 0, 0, 0));
        ElementRepo.save(new Element("...", "Ne", "Nonmetal", 10, 0, 0, 0));
        // period 3
        ElementRepo.save(new Element("Sodium", "Na", "Metal", 11, 0, 0, 0));
        ElementRepo.save(new Element("Magnesium", "Mg", "Metal", 12, 0, 0, 0));
        ElementRepo.save(new Element("Aluminium", "Al", "Metal", 13, 0, 0, 0));
        ElementRepo.save(new Element("Silicon", "Si", "Metal", 14, 0, 0, 0));
        ElementRepo.save(new Element("Phosphorus", "P", "Nonmetal", 15, 0, 0, 0));
        ElementRepo.save(new Element("Sulfur", "S", "Nonmetal", 16, 0, 0, 0));
        ElementRepo.save(new Element("Chlorine", "Cl", "Nonmetal", 17, 0, 0, 0));
        ElementRepo.save(new Element("Argon", "Ar", "Nonmetal", 18, 0, 0, 0));
        // period 4
        ElementRepo.save(new Element("Potassium", "K", "Metal", 19, 0, 0, 0));
        ElementRepo.save(new Element("...", "Ca", "Metal", 20, 0, 0, 0));
        ElementRepo.save(new Element("...", "Sc", "Metal", 21, 0, 0, 0));
        ElementRepo.save(new Element("Titanium", "Ti", "Metal", 22, 0, 0, 0));
        ElementRepo.save(new Element("...", "V", "Metal", 23, 0, 0, 0));
        ElementRepo.save(new Element("...", "Cr", "Metal", 24, 0, 0, 0));
        ElementRepo.save(new Element("...", "Mn", "Metal",25, 0, 0, 0));
        ElementRepo.save(new Element("Iron", "Fe", "Metal" , 26, 0, 0, 0));
		ElementRepo.save(new Element("...", "Co", "Metal", 27, 0, 0, 0));
		ElementRepo.save(new Element("...", "Ni", "Metal", 28, 0, 0, 0));
		ElementRepo.save(new Element("...", "Cu", "Metal", 29, 0, 0, 0));
		ElementRepo.save(new Element("...", "Zn", "Metal", 30, 0, 0, 0));
		ElementRepo.save(new Element("...", "Ga", "Metal", 31, 0, 0, 0));
		ElementRepo.save(new Element("...", "Ge", "Metalloid", 32, 0, 0, 0));
		ElementRepo.save(new Element("...", "As", "Metalloid", 33, 0, 0, 0));
		ElementRepo.save(new Element("...", "Se", "Nonmetal", 34, 0, 0, 0));
		ElementRepo.save(new Element("...", "Br", "Nonmetal", 35, 0, 0, 0));
		ElementRepo.save(new Element("...", "Kr", "Nonmetal", 36, 0, 0, 0));
		// period 5
		ElementRepo.save(new Element("...", "Rb", "Metal", 37, 0, 0, 0));
		ElementRepo.save(new Element("...", "Sr", "Metal", 38, 0, 0, 0));
		ElementRepo.save(new Element("...", "Y", "Metal", 39, 0, 0, 0));
		ElementRepo.save(new Element("...", "Zr", "Metal", 40, 0, 0, 0));
		ElementRepo.save(new Element("...", "Nb", "Metal", 41, 0, 0, 0));
		ElementRepo.save(new Element("...", "Mo", "Metal", 42, 0, 0, 0));
		ElementRepo.save(new Element("...", "Te", "Metal", 43, 0, 0, 0));
		ElementRepo.save(new Element("...", "Ru", "Metal", 44, 0, 0, 0));
		ElementRepo.save(new Element("...", "Rh", "Metal", 45, 0, 0, 0));
		ElementRepo.save(new Element("...", "Pd", "Metal", 46, 0, 0, 0));
		ElementRepo.save(new Element("...", "Pd", "Metal", 47, 0, 0, 0));
		ElementRepo.save(new Element("...", "Ag", "Metal", 48, 0, 0, 0));
		ElementRepo.save(new Element("...", "Cd", "Metal", 49, 0, 0, 0));
		ElementRepo.save(new Element("...", "Sn", "Metal", 50, 0, 0, 0));
		ElementRepo.save(new Element("...", "Sb", "Metalloid", 51, 0, 0, 0));
		ElementRepo.save(new Element("...", "Te", "Metalloid", 52, 0, 0, 0));
		ElementRepo.save(new Element("...", "I", "Nonmetal", 53, 0, 0, 0));
		ElementRepo.save(new Element("...", "Xe", "Nonmetal", 54, 0, 0, 0));
		// period 6
		ElementRepo.save(new Element("...", "Cs", "Metal", 55, 0, 0, 0));
		ElementRepo.save(new Element("...", "Ba", "Metal", 56, 0, 0, 0));
		ElementRepo.save(new Element("...", "La", "Metal", 57, 0, 0, 0));

		ElementRepo.save(new Element("...", "Hf", "Metal", 72, 0, 0, 0));
		ElementRepo.save(new Element("...", "Ta", "Metal", 73, 0, 0, 0));
		ElementRepo.save(new Element("...", "W" ,"Metal", 74, 0, 0, 0));
		ElementRepo.save(new Element("...", "Re", "Metal", 75, 0, 0, 0));
		ElementRepo.save(new Element("...", "Os", "Metal", 76, 0, 0, 0));
		ElementRepo.save(new Element("...", "Ir", "Metal", 77, 0, 0, 0));
		ElementRepo.save(new Element("...", "Pt", "Metal", 78, 0, 0, 0));
		ElementRepo.save(new Element("...", "Au", "Metal", 79, 0, 0, 0));
		ElementRepo.save(new Element("...", "Hg", "Metal", 80, 0, 0, 0));
		ElementRepo.save(new Element("...", "Tl", "Metal", 81, 0, 0, 0));
		ElementRepo.save(new Element("...", "Pb", "Metal", 82, 0, 0, 0));
		ElementRepo.save(new Element("...", "Bi", "Metal", 83, 0, 0, 0));
		ElementRepo.save(new Element("...", "", "Metalloid", 84, 0, 0, 0));
		ElementRepo.save(new Element("...", "", "Metalloid", 85, 0, 0, 0));
		ElementRepo.save(new Element("...", "", "Nonmetal", 86, 0, 0, 0));
		// period 7
		ElementRepo.save(new Element("...", "Fr", "Metal", 87, 0, 0, 0));
		ElementRepo.save(new Element("...", "Ra", "Metal", 88, 0, 0, 0));
		ElementRepo.save(new Element("...", "Ac", "Metal", 89, 0, 0, 0));

		ElementRepo.save(new Element("...", "Rf", "Metal", 104, 0, 0, 0));
		ElementRepo.save(new Element("...", "Db", "Metal", 105, 0, 0, 0));
		ElementRepo.save(new Element("...", "Sg", "Metal", 106, 0, 0, 0));
		ElementRepo.save(new Element("...", "Bh", "Metal", 107, 0, 0, 0));
		ElementRepo.save(new Element("...", "Hs", "Metal", 108, 0, 0, 0));
		ElementRepo.save(new Element("...", "Mt", "Metal", 109, 0, 0, 0));
		ElementRepo.save(new Element("...", "Ds", "Metal", 110, 0, 0, 0));
		ElementRepo.save(new Element("...", "Rg", "Metal", 111, 0, 0, 0));
		ElementRepo.save(new Element("...", "Cn", "Metal", 112, 0, 0, 0));
		ElementRepo.save(new Element("...", "",  "Metal", 113, 0, 0, 0));
		ElementRepo.save(new Element("...", "Fl", "Metal", 114, 0, 0, 0));
		ElementRepo.save(new Element("...", "",  "Metal", 115, 0, 0, 0));
		ElementRepo.save(new Element("...", "Lv", "Metal", 116, 0, 0, 0));
		ElementRepo.save(new Element("...", "", "Metal", 117, 0, 0, 0));
		ElementRepo.save(new Element("...", "", "Nonmetal", 118, 0, 0, 0));        
        System.out.println("Data creation complete...");
    }
    
	// READ
	// 1. Show all the data
	public void showAllElements() {
		ElementRepo.findAll().forEach(item -> System.out.println(getItemDetails(item)));
	}
 
	// 2. Get item by symbol
	public void getElementBySymbol(String symbol) {
		System.out.println("Getting item by symbol: " + symbol);
		Element elm = ElementRepo.findItemBySymbol(symbol);
		System.out.println(getItemDetails(elm));
	}
 
	// 3. Get name and symbol of a all items of a particular family
	public void getElementsByFamily(String family) {
		System.out.println("Getting items for the family " + family);
		List<Element> list = ElementRepo.findAll(family);
		list.forEach(item -> System.out.println("Name: " + item.getName() + ", Symbol: " + item.getSymbol()));
	}
 
	// 4. Get count of documents in the collection
	public void findCountOfElements() {
		long count = ElementRepo.count();
		System.out.println("Number of documents in the collection: " + count);
	}
 
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
			", \nItem Atomic Number: " + item.getAtomicNum() +
			", \nItem Family: " + item.getFamily()
		;
	}
 
	public void run(String ...args) {
		System.out.println("-------------CREATE ELEMENTS -------------------------------\n");
		createPeriodicElements();
		System.out.println("\n----------------SHOW ALL ELEMENTS ---------------------------\n");
		showAllElements();
		System.out.println("\n--------------GET ELEMENTS BY SYMBOL-----------------------------------\n");
		getElementBySymbol("He");
		System.out.println("\n-----------GET ELEMENTS BY FAMILY ---------------------------------\n");
		getElementsByFamily("Metal");
		// System.out.println("\n-----------UPDATE CATEGORY NAME OF SNACKS CATEGORY----------------\n");
		// updateCategoryName("snacks");
		// System.out.println("\n----------DELETE A GROCERY ITEM----------------------------------\n");
		// deleteGroceryItem("Kodo Millet"); 
		System.out.println("\n------------FINAL COUNT OF ELEMENTS -------------------------\n");
		findCountOfElements();
		System.out.println("\n-------------------THANK YOU---------------------------");
	}
}
