// a domain class
package edu.metrostate.ics499.team2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("elements")
public class Element implements Comparable <Element> {
	
	@Id
	private String id;												// primary key per @Id
	private String symbol;											// symbol (was not working as primary key?)

	private String name;											// name
	private String family;											// family: metal, metalloid, non-metal
	private int atomicNumber;										// atomic number: invariant, identifies the element, represents the number
																	// the number of protons in the nucleus of the atom
	private double massNumber;										// mass number: varies, identifies the isotope, represents the sum of protons
																	// and neutrons in the nucleus of the atom
	private int valence;											// valence: outer energy level electrons involved in covalent bond
	private int ionicCharge;										// ionicCharge: positive or negative integer; relates to one or more lost or gained electrons
																	// giving the atom a positive or negative electric charge (cation +, anion -), inolved in ionic bond
	
	//	constructor
	public Element(String name, String symbol, String family, int atomicNumber, double massNumber, int valence, int ionicCharge) {
		this.name = name;
		this.family = family;
		this.symbol = symbol;
		this.atomicNumber = atomicNumber;
		this.massNumber = massNumber;
		this.valence = valence;
		this.ionicCharge = ionicCharge;
	}
	
	// getters
	// name
	public String getName() {
		return this.name;
	}
	// family
	public String getFamily() {
		return this.family;
	}
	// symbol
	public String getSymbol() {
		return this.symbol;
	}
	// atomic number
	public int getAtomicNum() {
		return this.atomicNumber;
	}
	// mass number
	public double getMassNumber() {
		return this.massNumber;
	}
	// valence
	public int getvalence() {
		return this.valence;
	}
	// ionic charge
	public int getIonicCharge() {
		return this.ionicCharge;
	}
	
	// setters
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public void setAtomicNumber(int atomicNumber) {
		this.atomicNumber = atomicNumber;
	}

	public void setMassNumber(double massNumber) {
		this.massNumber = massNumber;
	}

	public void setValence(int valence) {
		this.valence = valence;
	}

	public void setIonicCharge(int ionicCharge) {
		this.ionicCharge = ionicCharge;
	}
	
	// to string
	public String toString() {
		String output = "";
		output += String.format("%-20s%-20d%-20s%-20s", this.name, this.atomicNumber, this.symbol, this.family);
		return output;
	}
	
	// equals
	public boolean equals(Object other) {
		if (other instanceof Element) {
			Element candidate = (Element) other;
			return name.equalsIgnoreCase(candidate.name) && candidate.atomicNumber == atomicNumber && 
					symbol.equalsIgnoreCase(candidate.symbol);
		} else		
			return false;
	}
	
	// compare
	@Override
	public int compareTo(Element o) {
		if (this.atomicNumber < o.atomicNumber)
			return -1;
		else if (this.atomicNumber > o.atomicNumber)
			return 1;
		else return 0;
	}

}
