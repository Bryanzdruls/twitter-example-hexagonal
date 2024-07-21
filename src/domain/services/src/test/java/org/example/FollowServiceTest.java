package org.example;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class FollowServiceTest {

    private FollowService followService;
    @Mock
    private Repository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
        followService = new FollowService();

        // Configura el mock de Repository
        List<User> users = new ArrayList<>();
        User ivan = new User(
                UserTag.of("ivan"),
                UserName.of("ivan"),
                new HashSet<>(),
                new HashSet<>(),
                new ArrayList<>()
        );
        users.add(ivan);
        doReturn(users).when(repository).getUsers();

        try {
            java.lang.reflect.Field field = FollowService.class.getDeclaredField("repository");
            field.setAccessible(true);
            field.set(followService, repository);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        User ivan = new User(
                UserTag.of("ivan"),
                UserName.of("ivan"),
                new HashSet<>(),
                new HashSet<>(),
                new ArrayList<>()
        );

        // Configura el comportamiento esperado de execute
        String destiny = "ivan";

        // Llama al método y verifica el resultado
        User ivanReturn = followService.execute(destiny);
        assertEquals(ivan.getUserName().value(), ivanReturn.getUserName().value());
    }

    @Test
    void testExecuteUserNotFound() {
        String destiny = "nonexistentUser";

        // Ejecuta el método
        User userReturn = followService.execute(destiny);

        // Verifica que el resultado sea null
        assertEquals(null, userReturn);
    }

    @Test
    void testUnimplementedMethod(){
        assertThrows(RuntimeException.class, ()->followService.execute(null, "any","any"));
    }
}
