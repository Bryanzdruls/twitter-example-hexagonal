package org.example;

import org.example.post.Post;

import org.example.usecases.GenerateUsersUseCase;
import org.example.user.User;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class EntryPoint {

    private final GenerateUsersUseCase generateUsersUseCase;

    private final FollowService followService;

    private final Repository repository;
    private static EntryPoint entryPoint;
    private EntryPoint(){
        generateUsersUseCase = new GenerateUsersUseCase();
        repository = Repository.repositoryInstance();
        followService = new FollowService();
        entryMenu();
    };

    public static EntryPoint entryPointInstance(){
        if (entryPoint == null){
            entryPoint = new EntryPoint();
        }
        return entryPoint;
    }

    public void entryMenu(){
        Scanner scanner = new Scanner(System.in);
        this.repository.setUsers(this.generateUsersUseCase.execute());
        int option = 0;

        while (option != 3) {
            System.out.println("Ingrese algun comando:");
            System.out.println("1. follow @origin @destino");
            System.out.println("2. post @origen message compuesto");
            System.out.println("3. wall @destino");
            System.out.print("Usuarios disponibles ");
            for (User user :
                    repository.getUsers()) {
                System.out.print(user.getUserTag().value() + ", ");
            }
            System.out.println();
            System.out.print("> ");

            String comando = scanner.nextLine();

            System.out.println("su comando fue: " + comando);
            if (comando.toLowerCase().startsWith("follow ")) {
                String[] parts = comando.split(" ");
                if (parts.length == 3) {
                    String origin = parts[1].substring(1);
                    String destino = parts[2].substring(1);
                    handleFollowCommand(origin, destino);
                } else {
                    System.out.println("Comando 'follow' no válido. Uso: follow @origin @destino");
                }
            } else if (comando.toLowerCase().startsWith("post ")) {
                String[] parts = comando.split(" ", 3);
                if (parts.length == 3) {
                    String origin = parts[1].substring(1); // Remove the '@'
                    String message = parts[2];
                    handlePostCommand(origin, message);
                } else {
                    System.out.println("Comando 'post' no válido. Uso: post @Origen message compuesto");
                }
            } else if (comando.toLowerCase().startsWith("wall ")) {
                String[] parts = comando.split(" ");
                if (parts.length == 2) {
                    String destino = parts[1].substring(1); // Remove the '@'
                    handleWallCommand(destino);
                } else {
                    System.out.println("Comando 'wall' no válido. Uso: wall @destino");
                }
            } else {
                System.out.println("Comando no reconocido. Por favor intente de nuevo.");
            }
        }
        scanner.close();
    }
    private void handleFollowCommand(String origin, String destino) {
        User userBase = repository.getUsers().stream()
                .filter(user -> user.getUserTag().value().equals(origin))
                .findFirst().get();

        User userToFollow =followService.execute(destino);
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

        Post newPost = new Post(LocalTime.now(),origin,message);

        userBase.getTimeline().add(newPost);
         System.out.println(userBase.getUserName().value()+ " Posted -> '"+ newPost.getMessage() + "' @"+ newPost.getPostHour().format(DateTimeFormatter.ofPattern("HH:mm")));
    }

    private void handleWallCommand(String destino) {
        System.out.println("Mostrando wall de " + destino);
        User userBase = repository.getUsers().stream()
                .filter(user -> user.getUserTag().value().equals(destino))
                .findFirst().get();

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

        // Imprimir las listas ordenadas
        sortedPosts.forEach(post -> {
            System.out.println(post.getMessage()+ " @"+ post.getTagName()+" @"+ post.getPostHour().format(DateTimeFormatter.ofPattern("HH:mm")));
        });
    }
}