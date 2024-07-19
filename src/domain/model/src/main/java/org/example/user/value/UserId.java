package org.example.user.value;

import org.example.generic.factories.Identity;

public class UserId extends Identity {

    public UserId(){
        super();
    }

    public UserId(String id) {
        super(id);
    }

    public static UserId of(String uuid){
        return new UserId(uuid);
    }
}

