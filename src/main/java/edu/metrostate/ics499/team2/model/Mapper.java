package edu.metrostate.ics499.team2.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public UserDTO toDto(RegisteredUser user) {
        String name = user.getName();
        List<String> roles = user
          .getRoles()
          .stream()
          .map(Role::getName)
          .collect(toList());

        return new UserDTO(name);
    }

    public RegisteredUser toUser(UserCreationDTO userDTO) {
        return new RegisteredUser(userDTO);
    }
}
