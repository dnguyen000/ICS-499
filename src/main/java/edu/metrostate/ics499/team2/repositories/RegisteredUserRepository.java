package edu.metrostate.ics499.team2.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.metrostate.ics499.team2.model.RegisteredUser;

@Repository
public interface RegisteredUserRepository extends MongoRepository<RegisteredUser, String> {
	RegisteredUser findRegisteredUserByEmail(String email);
	RegisteredUser findRegisteredUserByUsername(String username);

	// getAllUsers first 100 da da da
	//	@Query("")
	//	List<RegisteredUser> getUsers =
}
