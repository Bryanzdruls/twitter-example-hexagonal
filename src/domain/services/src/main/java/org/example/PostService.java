package org.example;

import org.example.generic.IExecuteWithStringParam;
import org.example.post.Post;
import org.example.user.User;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PostService implements IExecuteWithStringParam<User> {
    @Override
    public User execute(String origin) {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public User execute(User userBase, String origin, String message) {
        Post newPost = new Post(LocalTime.now(),origin,message);
        userBase.getTimeline().add(newPost);
        System.out.println(userBase.getUserName().value()+ " Posted -> '"+ newPost.getMessage() + "' @"+ newPost.getPostHour().format(DateTimeFormatter.ofPattern("HH:mm")));
        return userBase;
    }
}
