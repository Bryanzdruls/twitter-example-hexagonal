package org.example.usecases;

import org.example.MenusService;
import org.example.gateways.IUseCase;
import org.example.user.User;

import java.util.List;

public class EnterAsUserUseCase implements IUseCase<User> {

    private final MenusService menusService;

    public EnterAsUserUseCase() {
        menusService = new MenusService();
    }

    @Override
    public User execute(User user) {
        menusService.userMenu(user);
        return null;
    }
}
