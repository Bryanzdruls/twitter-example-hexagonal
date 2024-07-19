package org.example.user.value;

import org.example.generic.factories.ValueObject;

public class UsersFollowing implements ValueObject<String> {

    private final String userFollowing;

    private UsersFollowing(String userFollowing) {
        if(!userFollowing.isEmpty()){
            this.userFollowing = userFollowing;
        }else throw new IllegalArgumentException("userFollowing cannot be empty");
    }

    public static UsersFollowing of(String userFollowing){return new UsersFollowing(userFollowing);}
    @Override
    public String value() {
        return this.userFollowing;
    }
}