package org.example;

import org.example.generic.IExecute;
import org.example.post.Post;
import org.example.user.User;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WallService implements IExecute<User> {

    @Override
    public User execute(User userBase) {
        List<List<Post>> generalPostUnorder = new ArrayList<>();
        userBase.getUsersFollowings().forEach(user -> {
            List<Post> postUser = new ArrayList<>(user.getTimeline());
            generalPostUnorder.add(postUser);
        } );
        System.out.println("Dashboard @"+ userBase.getUserName().value());
        List<Post> sortedPosts = generalPostUnorder.stream()
                .flatMap(List::stream)
                .sorted(Comparator.comparing(Post::getPostHour))
                .collect(Collectors.toList());
        sortedPosts.forEach(post -> {
            System.out.println(post.getMessage()+ " @"+ post.getTagName()+" @"+ post.getPostHour().format(DateTimeFormatter.ofPattern("HH:mm")));
        });
        return userBase;
    }
}
