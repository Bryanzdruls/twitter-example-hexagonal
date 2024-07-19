package org.example.user.value;

import org.example.generic.factories.ValueObject;

public class UserFollowers implements ValueObject<String> {

    private final String userFollower;

    private UserFollowers(String userFollower) {
        if(!userFollower.isEmpty()){
            this.userFollower = userFollower;
        }else throw new IllegalArgumentException("userFollower cannot be empty");
    }

    public static UserFollowers of(String userFollower){return new UserFollowers(userFollower);}
    @Override
    public String value() {
        return this.userFollower;
    }
}