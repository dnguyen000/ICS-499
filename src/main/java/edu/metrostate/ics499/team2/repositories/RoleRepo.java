package edu.metrostate.ics499.team2.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.metrostate.ics499.team2.model.Role;

@Repository
public interface RoleRepo extends MongoRepository<Role, Long> {
	Role findByName(String name);
}
