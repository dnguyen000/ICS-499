package edu.metrostate.ics499.team2.model;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public UserDTO toDto(RegisteredUser user) {
        String name = user.getName();
        try {
	        List<String> roles = user
	          .getRoles()
	          .stream()
	          .map(Role::getName)
	          .collect(Collectors.toList());
	        return new UserDTO(name, roles);
        } catch(Exception e) {
        	System.out.println(e.getMessage());
        	return new UserDTO(name);
        }
        
    }

    public RegisteredUser toUser(UserCreationDTO userDTO) {
        return new RegisteredUser(userDTO);
    }
}
