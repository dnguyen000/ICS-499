package edu.metrostate.ics499.team2.repositories;

import java.util.List;
import com.smart.molecule.entity.Element;
import com.smart.molecule.entity.Molecule;

@Repository
public interface MoleculeRepository extends MongoRepository<Molecule, String>{
	Molecule findBySymbol(String symbol);
}
