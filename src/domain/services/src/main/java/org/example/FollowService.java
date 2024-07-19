package org.example;

import org.example.generic.IExecuteFollow;
import org.example.user.User;

import java.util.Optional;

public class FollowService implements IExecuteFollow<User> {
    private final Repository repository;

    public FollowService() {
        repository = Repository.repositoryInstance();
    }

    @Override
    public User execute(String name) {
        System.out.println("El nombre es: "+ name);
        Optional<User> userOptional = repository.getUsers().stream()
                .filter(user -> user.getUserTag().equals(name))
                .findFirst();

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.println("Usuario encontrado: " + user.getUserName());
            return user;
        } else {
            System.out.println("Usuario no encontrado");
            return null;
        }
    }
}
