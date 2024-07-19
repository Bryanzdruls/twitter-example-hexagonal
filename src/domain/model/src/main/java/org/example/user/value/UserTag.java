package org.example.user.value;

import org.example.generic.factories.ValueObject;

public class UserTag  implements ValueObject<String> {

    private final String userTag;

    private UserTag(String userTag) {
        if(!userTag.isEmpty()){
            this.userTag = userTag;
        }else throw new IllegalArgumentException("userTag cannot be empty");
    }

    public static UserTag of(String userTag){return new UserTag(userTag);}
    @Override
    public String value() {
        return this.userTag;
    }
}
