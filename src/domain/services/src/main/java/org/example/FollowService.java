package org.example;

import org.example.generic.IExecuteWithStringParam;
import org.example.user.User;

import java.util.Optional;

public class FollowService implements IExecuteWithStringParam<User> {
    private final Repository repository;

    public FollowService() {
        repository = Repository.repositoryInstance();
    }

    @Override
    public User execute(String name) {
        System.out.println("El nombre es: "+ name);
        Optional<User> userOptional = repository.getUsers().stream()
                .filter(user -> user.getUserTag().value().equals(name))
                .findFirst();

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.println("Usuario encontrado: " + user.getUserName().value());
            return user;
        } else {
            System.out.println("No se encontro ningun usuario llamado @" + name);
            return null;
        }
    }

    @Override
    public User execute(User generic, String value, String value2) {
        throw new RuntimeException("Method not implemented");
    }
}
