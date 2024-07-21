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
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class WallServiceTest {

    private WallService wallService;

    @BeforeEach
    void setUp() {
        wallService = new WallService();
    }

    @Test
    void testWallService() {
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
                new ArrayList<>()
        );
        User ivan = new User(
                UserTag.of("ivan"),
                UserName.of("ivan"),
                new HashSet<>(),
                new HashSet<>(),
                new ArrayList<>()
        );

        // Agregar seguidores
        alicia.getUsersFollowings().add(alfonso);
        alicia.getUsersFollowings().add(ivan);

        // Agregar posts
        Post post1 = new Post(LocalTime.of(14, 41),"ivan","Hola soy Ivan" );
        Post post2 = new Post(LocalTime.of(14, 46),"ivan","Espera" );
        Post post3 = new Post(LocalTime.of(14, 42),"alfonso","Broken one" );

        ivan.getTimeline().add(post1);
        ivan.getTimeline().add(post2);
        alfonso.getTimeline().add(post3);

        User result = wallService.execute(alicia);

        assertEquals(alicia, result);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        wallService.execute(alicia);

        String expectedOutput = "Dashboard @alicia\n" +
                "Hola soy Ivan @ivan @14:41\n" +
                "Broken one @alfonso @14:42\n" +
                "Espera @ivan @14:46\n";
        String actualOutput = outContent.toString().replaceAll("\r\n", "\n");
        assertEquals(expectedOutput, actualOutput);
    }
}