package org.example.usecases;

import org.example.post.Post;
import org.example.user.User;
import org.example.user.value.UserName;
import org.example.user.value.UserTag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GenerateUsersUseCaseTest {
    private GenerateUsersUseCase generateUsersUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        generateUsersUseCase = spy(new GenerateUsersUseCase());


    }
    @Test
    void execute() {
        List<User> usersFinal = new ArrayList<User>();
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
        usersFinal.add(alicia);
        usersFinal.add(ivan);
        usersFinal.add(alfonso);

        when(generateUsersUseCase.execute()).thenReturn(usersFinal);

        List<User> usersGenerated = generateUsersUseCase.execute();
        assertEquals(usersFinal, usersGenerated);
    }
}