package org.example;

import org.example.post.Post;
import org.example.user.User;
import org.example.user.value.UserName;
import org.example.user.value.UserTag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {
    private PostService postService;

    @BeforeEach
    void setUp() {
        postService = new PostService();
    }
    @Test
    void execute() {
        User alicia = new User(
                UserTag.of("alicia"),
                UserName.of("alicia"),
                new HashSet<>(),
                new HashSet<>(),
                new ArrayList<>()
        );
        Post post1 = new Post(LocalTime.of(14, 41),"alicia","Hola soy Alicia" );
        alicia.getTimeline().add(post1);

        User result = postService.execute(alicia, post1.getTagName(), post1.getMessage());
        assertEquals(alicia, result);


        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        postService.execute(alicia, post1.getTagName(), post1.getMessage());

        String expectedOutput = "alicia Posted -> 'Hola soy Alicia' @"+LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + "\n" ;
        String actualOutput = outContent.toString().replaceAll("\r\n", "\n");
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testMethodNotImplemented(){
        assertThrows(RuntimeException.class, ()->{
            postService.execute("any");
        });
    }
}