package org.example.usecases;

import org.example.gateways.IUseCase;
import org.example.gateways.IUseCaseWithoutParams;
import org.example.user.User;

import java.util.List;

public class GenerateUsersUseCase implements IUseCaseWithoutParams<List<User>> {

    @Override
    public List<User> execute() {
        return User.generateUsers();
    }
}
