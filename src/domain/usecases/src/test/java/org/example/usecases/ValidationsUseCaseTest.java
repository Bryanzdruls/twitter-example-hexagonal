package org.example.usecases;

import org.example.FollowService;
import org.example.PostService;
import org.example.Repository;
import org.example.WallService;
import org.example.post.Post;
import org.example.user.User;
import org.example.user.value.UserName;
import org.example.user.value.UserTag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ValidationsUseCaseTest {

    private ValidationsUseCase validationsUseCase;

    @Mock
    private Repository repository;

    @Mock
    private FollowService followService;

    @Mock
    private PostService postService;

    @Mock
    private WallService wallService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validationsUseCase = spy(new ValidationsUseCase());

        // Mockear el repositorio
        List<User> users = new ArrayList<>();
        User ivan = new User(UserTag.of("ivan"), UserName.of("Ivan"), new HashSet<>(), new HashSet<>(), new ArrayList<Post>());
        User alicia = new User(UserTag.of("alicia"), UserName.of("Alicia"), new HashSet<>(), new HashSet<>(), new ArrayList<Post>());
        User alfonso = new User(UserTag.of("alfonso"), UserName.of("Alfonso"), new HashSet<>(), new HashSet<>(), new ArrayList<Post>());
        users.add(ivan);
        users.add(alicia);
        users.add(alfonso);

        when(repository.getUsers()).thenReturn(users);
        validationsUseCase = spy(new ValidationsUseCase(repository, followService, postService, wallService));;
    }
    @Test
    void testHandleFollowCommand() {
        String command = "follow @alicia @ivan";

        validationsUseCase.execute(command);
        verify(validationsUseCase).handleFollowCommand("alicia", "ivan");
    }

    @Test
    void testHandlePostCommand() {
        String command = "post @alfonso probando";

        validationsUseCase.execute(command);
        verify(validationsUseCase).handlePostCommand("alfonso", "probando");
    }
    @Test
    void testHandleWallCommandIsCalled() {
        String command = "wall @alicia";

        validationsUseCase.execute(command);
        verify(validationsUseCase).handleWallCommand("alicia");
    }

    @Test
    void testHandlePostCommandNameWrong() {
        String command = "post @aaalfonso rpobando";

        assertThrows(NoSuchElementException.class, ()->{
            validationsUseCase.execute(command);
        });
    }
    @Test
    void testHandleFollowCommandNameWrong() {
        String command = "follow @aliciaa @ivann";

        assertThrows(NoSuchElementException.class, ()->{
            validationsUseCase.execute(command);
        });
    }
    @Test
    void testHandleWallCommandIsCalledNameWrong() {
        String command = "wall @aliciaa";

        assertThrows(NoSuchElementException.class, ()->{
            validationsUseCase.execute(command);
        });
    }

    @Test
    void testHandleFollowCommandBadCommand() {
        String command = "follow      licia @ivan";

        validationsUseCase.execute(command);
        verify(validationsUseCase, never()).handleFollowCommand("alicia", "ivan");
    }

    @Test
    void testHandlePostCommandBadCommand() {
        String command = "post @alfonso";

        validationsUseCase.execute(command);
        verify(validationsUseCase, never()).handlePostCommand("alfonso", "probando");
    }
    @Test
    void testHandleWallCommandIsCalledBadCommand() {
        String command = "wall  icia";

        validationsUseCase.execute(command);
        verify(validationsUseCase, never()).handleWallCommand("alicia");
    }

    @Test
    void testBadCommand() {
        String command = "badcomaand  ";

        validationsUseCase.execute(command);
        verify(validationsUseCase, never()).handleWallCommand("any");
        verify(validationsUseCase, never()).handlePostCommand("any","any");
        verify(validationsUseCase, never()).handleFollowCommand("any","any");
    }


}