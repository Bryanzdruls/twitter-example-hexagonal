package org.example;

import org.example.generic.IExecuteWithStringParam;
import org.example.user.User;

public class PostService implements IExecuteWithStringParam<User> {
    @Override
    public User execute(String messageToPost) {

        return null;
    }
}
