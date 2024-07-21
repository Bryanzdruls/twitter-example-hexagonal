package org.example.user;

import org.example.post.Post;
import org.example.user.value.UserTag;
import org.example.user.value.UserFollowers;
import org.example.user.value.UserId;
import org.example.user.value.UserName;
import org.example.user.value.UsersFollowing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {

    private UserId userId;
    private UserTag userTag;

    private UserName userName;

    private Set<User> userFollowers;

    private Set<User> usersFollowings;


    private List<Post> timeline;

    public static List<User> generateUsers(){
        List<User> users = new ArrayList<User>();
        User alicia = new User(
                UserTag.of("alicia"),
                UserName.of("alicia"),
                new HashSet<>(),
                new HashSet<>(),
                new ArrayList<>()
        );
        User alfonso = new User(
                UserTag.of("alfonso"),
                UserName.of("alfonso"),
                new HashSet<>(),
                new HashSet<>(),
                new ArrayList<>());
        User ivan = new User(
                UserTag.of("ivan"),
                UserName.of("ivan"),
                new HashSet<>(),
                new HashSet<>(),
                new ArrayList<>()
        );
        users.add(alicia);
        users.add(ivan);
        users.add(alfonso);
        return users;
    }

    public User( UserTag userTag, UserName userName, Set<User> userFollowers, Set<User> usersFollowings, List<Post> timeline) {
        this.userId = new UserId();
        this.userTag = userTag;
        this.userName = userName;
        this.userFollowers = userFollowers;
        this.usersFollowings = usersFollowings;
        this.timeline = timeline;
    }

    public UserTag getUserTag() {
        return userTag;
    }

    public UserName getUserName() {
        return userName;
    }

    public Set<User> getUserFollowers() {
        return userFollowers;
    }


    public Set<User> getUsersFollowings() {
        return usersFollowings;
    }

    public List<Post> getTimeline() {
        return timeline;
    }

}

