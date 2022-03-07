// the repository class implements CRUD actions
package edu.metrostate.ics499.team2.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import edu.metrostate.ics499.team2.model.Element;

// Domain class: Element, primary key type: String
public interface ElementRepository extends MongoRepository<Element, String> {

    @Query("{symbol:'?0'}")
    Element findElementBySymbol(String symbol);

    Element getElementByAtomicNumber(int atomicSymbol);

}
