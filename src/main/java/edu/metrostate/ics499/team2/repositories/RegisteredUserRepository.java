package edu.metrostate.ics499.team2.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.metrostate.ics499.team2.model.RegisteredUser;

@Repository
public interface RegisteredUserRepository extends MongoRepository<RegisteredUser, String>{
	RegisteredUser findByUserId(String Id);
	RegisteredUser findByEmail(String email);
	RegisteredUser findByEmailAndPassword(String email, String Password);
}
