package edu.metrostate.ics499.team2.appRole.model.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.metrostate.ics499.team2.appRole.model.Users;

@Repository
@Transactional(readOnly = true)
public interface UsersRepository extends MongoRepository <Users , String> {
	Users findByEmail(String email);
	Users findByUsrename(String username);

   

}