package edu.metrostate.ics499.team2.services;

import edu.metrostate.ics499.team2.model.Molecule;
import edu.metrostate.ics499.team2.repositories.MoleculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MoleculeServiceImpl implements MoleculeService {

    @Autowired
    private MoleculeRepository moleculeRepo;


    @Override
    public List<Molecule> findAll() {
        return moleculeRepo.findAll();
    }

    @Override
    public Molecule findBySymbol(String symbol) {
        return this.moleculeRepo.findBySymbol(symbol);
    }

    @Override
    public Molecule save(Molecule el) {
        return moleculeRepo.save(el);
    }

    @PostConstruct
    public void addMolecules() {
        List<Molecule> molecules = new ArrayList<>();
        molecules.add(new Molecule("Diatomic"));
        molecules.add(new Molecule("Triatomic"));
        molecules.add(new Molecule("Four atoms"));
        molecules.add(new Molecule("Five atoms"));
        molecules.add(new Molecule("Six atoms"));
        molecules.add(new Molecule("Seven atoms"));
        molecules.add(new Molecule("Eight atoms"));
        molecules.add(new Molecule("Nine atoms"));
        molecules.add(new Molecule("Ten atoms"));
        moleculeRepo.saveAll(molecules);
    }
}


