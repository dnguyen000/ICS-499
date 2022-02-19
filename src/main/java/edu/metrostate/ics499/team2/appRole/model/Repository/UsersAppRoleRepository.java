package edu.metrostate.ics499.team2.appRole.model.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.metrostate.ics499.team2.appRole.model.UsersAppRole;

@Repository
@Transactional(readOnly = true)
public interface UsersAppRoleRepository extends MongoRepository <UsersAppRole, String> {
	Optional<UsersAppRole> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);
 

}
