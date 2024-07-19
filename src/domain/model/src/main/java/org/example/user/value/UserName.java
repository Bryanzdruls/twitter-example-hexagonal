package org.example.user.value;

import org.example.generic.factories.ValueObject;


public class UserName implements ValueObject<String> {

    private final String userName;

    private UserName(String userName) {
        if(!userName.isEmpty()){
            this.userName = userName;
        }else throw new IllegalArgumentException("userName cannot be empty");
    }

    public static UserName of(String userName){return new UserName(userName);}
    @Override
    public String value() {
        return this.userName;
    }
}

