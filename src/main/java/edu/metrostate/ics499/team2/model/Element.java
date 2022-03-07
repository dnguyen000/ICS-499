// a domain class
package edu.metrostate.ics499.team2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("new_elms")
public class Element {
	
	@Id
	private String id;
	private String atomicNumber;									// atomic number: invariant, identifies the element, represents
																	// the number of protons in the nucleus of the atom  (becomes _id in mongo)
	private String symbol;											// symbol
	private String name;											// name
	private String atomicMass;										// mass number: varies, identifies the isotope, represents the sum of protons
																	// and neutrons in the nucleus of the atom
	private String cpkHexColor;										// color
	private String electronConfiguration;
	private String electronegativity;								// valence, outer energy level electrons involved in covalent bond ?
	private String atomicRadius;
	private String ionizationEnergy;
	private String electronAffinity;
	private String oxidationStates;
	private String standardState;
	private String meltingPoint;
	private String boilingPoint;
	private String density;
	private String groupBlock;										// group/family: metal, metalloid, non-metal etc.
	private String yearDiscovered;

	public Element(String id, String atomicNumber, String symbol, String name, String atomicMass, String cpkHexColor, String electronConfiguration, String electronegativity, String atomicRadius, String ionizationEnergy, String electronAffinity, String oxidationStates, String standardState, String meltingPoint, String boilingPoint, String density, String groupBlock, String yearDiscovered) {
		this.id = id;
		this.atomicNumber = atomicNumber;
		this.symbol = symbol;
		this.name = name;
		this.atomicMass = atomicMass;
		this.cpkHexColor = cpkHexColor;
		this.electronConfiguration = electronConfiguration;
		this.electronegativity = electronegativity;
		this.atomicRadius = atomicRadius;
		this.ionizationEnergy = ionizationEnergy;
		this.electronAffinity = electronAffinity;
		this.oxidationStates = oxidationStates;
		this.standardState = standardState;
		this.meltingPoint = meltingPoint;
		this.boilingPoint = boilingPoint;
		this.density = density;
		this.groupBlock = groupBlock;
		this.yearDiscovered = yearDiscovered;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAtomicNumber() {
		return atomicNumber;
	}

	public void setAtomicNumber(String atomicNumber) {
		this.atomicNumber = atomicNumber;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAtomicMass() {
		return atomicMass;
	}

	public void setAtomicMass(String atomicMass) {
		this.atomicMass = atomicMass;
	}

	public String getCpkHexColor() {
		return cpkHexColor;
	}

	public void setCpkHexColor(String cpkHexColor) {
		this.cpkHexColor = cpkHexColor;
	}

	public String getElectronConfiguration() {
		return electronConfiguration;
	}

	public void setElectronConfiguration(String electronConfiguration) {
		this.electronConfiguration = electronConfiguration;
	}

	public String getElectronegativity() {
		return electronegativity;
	}

	public void setElectronegativity(String electronegativity) {
		this.electronegativity = electronegativity;
	}

	public String getAtomicRadius() {
		return atomicRadius;
	}

	public void setAtomicRadius(String atomicRadius) {
		this.atomicRadius = atomicRadius;
	}

	public String getIonizationEnergy() {
		return ionizationEnergy;
	}

	public void setIonizationEnergy(String ionizationEnergy) {
		this.ionizationEnergy = ionizationEnergy;
	}

	public String getElectronAffinity() {
		return electronAffinity;
	}

	public void setElectronAffinity(String electronAffinity) {
		this.electronAffinity = electronAffinity;
	}

	public String getOxidationStates() {
		return oxidationStates;
	}

	public void setOxidationStates(String oxidationStates) {
		this.oxidationStates = oxidationStates;
	}

	public String getStandardState() {
		return standardState;
	}

	public void setStandardState(String standardState) {
		this.standardState = standardState;
	}

	public String getMeltingPoint() {
		return meltingPoint;
	}

	public void setMeltingPoint(String meltingPoint) {
		this.meltingPoint = meltingPoint;
	}

	public String getBoilingPoint() {
		return boilingPoint;
	}

	public void setBoilingPoint(String boilingPoint) {
		this.boilingPoint = boilingPoint;
	}

	public String getDensity() {
		return density;
	}

	public void setDensity(String density) {
		this.density = density;
	}

	public String getGroupBlock() {
		return groupBlock;
	}

	public void setGroupBlock(String groupBlock) {
		this.groupBlock = groupBlock;
	}

	public String getYearDiscovered() {
		return yearDiscovered;
	}

	public void setYearDiscovered(String yearDiscovered) {
		this.yearDiscovered = yearDiscovered;
	}
}
