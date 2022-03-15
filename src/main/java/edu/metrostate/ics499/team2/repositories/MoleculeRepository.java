package edu.metrostate.ics499.team2.repositories;

import edu.metrostate.ics499.team2.model.Molecule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoleculeRepository extends MongoRepository<Molecule, String> {
	Molecule findBySymbol(String symbol);
}
