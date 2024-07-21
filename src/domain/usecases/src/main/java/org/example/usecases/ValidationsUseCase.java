package org.example.usecases;

import org.example.FollowService;
import org.example.PostService;
import org.example.Repository;
import org.example.WallService;
import org.example.gateways.IUseCase;
import org.example.gateways.IUseCaseWithoutParams;
import org.example.post.Post;
import org.example.user.User;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ValidationsUseCase implements IUseCase<String> {
    private final Repository repository;

    private final FollowService followService;

    private final PostService postService;

    private final WallService wallService;

    public ValidationsUseCase() {
        repository = Repository.repositoryInstance();
        followService = new FollowService();
        postService = new PostService();
        wallService = new WallService();
    }

    @Override
    public String execute(String command) {
        if (command.toLowerCase().startsWith("follow ")) {
            String[] parts = command.split(" ");
            if (parts.length == 3) {
                String origin = parts[1].substring(1);
                String destino = parts[2].substring(1);
                handleFollowCommand(origin, destino);
            } else {
                System.out.println("Comando 'follow' no válido. Uso: follow @origin @destino");
            }
        } else if (command.toLowerCase().startsWith("post ")) {
            String[] parts = command.split(" ", 3);
            if (parts.length == 3) {
                String origin = parts[1].substring(1);
                String message = parts[2];
                handlePostCommand(origin, message);
            } else {
                System.out.println("Comando 'post' no válido. Uso: post @Origen message compuesto");
            }
        } else if (command.toLowerCase().startsWith("wall ")) {
            String[] parts = command.split(" ");
            if (parts.length == 2) {
                String destino = parts[1].substring(1);
                handleWallCommand(destino);
            } else {
                System.out.println("Comando 'wall' no válido. Uso: wall @destino");
            }
        } else if (command.toLowerCase().startsWith("exit")) {
            System.exit(0);
        }else{
            System.out.println("Comando no reconocido. Por favor intente de nuevo.");
        }
        return null;
    }

    private void handleFollowCommand(String origin, String destino) {
        User userBase = repository.getUsers().stream()
                .filter(user -> user.getUserTag().value().equals(origin))
                .findFirst().get();
        User userToFollow = followService.execute(destino);
        if (userToFollow != null) {
            userBase.getUsersFollowings().add(userToFollow);
            userToFollow.getUserFollowers().add(userBase);
            System.out.println(userBase.getUserName().value() + " empezo a seguir a " + userToFollow.getUserName().value());
        }
    }

    private void handlePostCommand(String origin, String message) {
        User userBase = repository.getUsers().stream()
                .filter(user -> user.getUserTag().value().equals(origin))
                .findFirst().get();
        userBase = postService.execute(userBase,origin,message);
    }

    private void handleWallCommand(String destino) {
        System.out.println("Mostrando wall de " + destino);
        User userBase = repository.getUsers().stream()
                .filter(user -> user.getUserTag().value().equals(destino))
                .findFirst().get();
        userBase = wallService.execute(userBase);
    }
}