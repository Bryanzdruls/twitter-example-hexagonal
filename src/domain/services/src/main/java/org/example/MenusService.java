package org.example;

import org.example.user.User;

import java.util.List;
import java.util.Scanner;

public class MenusService {

    private final FollowService followService;

    private final Repository repository;
    public MenusService() {
        followService = new FollowService();
        repository = Repository.repositoryInstance();
    }

    public void userMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        while (option != 4) {
            System.out.println("Menú:");
            System.out.println("1. Postear en timeline");
            System.out.println("2. Ver Dashboard");
            System.out.println("3. Seguir a alguien");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");

            option = scanner.nextInt();

            switch (option) {
                case 1 ->{

                }

                case 2 -> System.out.println("Has elegido la Opción 2");

                case 3 -> {
                    for (User userRepo:
                            repository.getUsers()) {
                        System.out.println(userRepo.getUserTag().value());
                    }
                    System.out.println("Ingrese el tag de la persona que quiere seguir");

                    String name = scanner.next();
                    User userToFollow = this.followService.execute(name);
                    if (userToFollow != null) {
                        user.getUsersFollowings().add(userToFollow);
                    }
                    System.out.println("avr");
                }

                case 4 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida, por favor intente de nuevo.");
            }
        }

        scanner.close();
    }
}
