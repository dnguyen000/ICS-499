package edu.metrostate.ics499.team2.services;

import edu.metrostate.ics499.team2.model.Molecule;

import java.util.List;

public interface MoleculeService {
    public List<Molecule> findAll();
    Molecule findBySymbol(String symbol);
    public Molecule save(Molecule el);
}

