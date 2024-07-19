package org.example;

import org.example.usecases.EnterAsUserUseCase;
import org.example.usecases.GenerateUsersUseCase;
import org.example.user.User;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class EntryPoint {

    private final GenerateUsersUseCase generateUsersUseCase;
    private final EnterAsUserUseCase enterAsUserUseCase;

    private final Repository repository;
    private static EntryPoint entryPoint;
    private EntryPoint(){
        generateUsersUseCase = new GenerateUsersUseCase();
        enterAsUserUseCase = new EnterAsUserUseCase();
        repository = Repository.repositoryInstance();
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
            System.out.println("1. Follow @origen @destino");
            System.out.println("2. post @Origen mensaje compuesto");
            System.out.println("3. wall @destino");
            System.out.print("Usuarios disponibles ");
            for (User user :
                    repository.getUsers()) {
                System.out.print(user.getUserTag().value() + ", ");
            }
            System.out.println();
            System.out.print("> ");
            //option = scanner.nextInt();
            String comando = scanner.nextLine();

            System.out.println("su comando fue: " + comando);
            if (comando.toLowerCase().startsWith("follow ")) {
                String[] parts = comando.split(" ");
                if (parts.length == 3) {
                    String origen = parts[1].substring(1);
                    String destino = parts[2].substring(1);
                    handleFollowCommand(origen, destino);
                } else {
                    System.out.println("Comando 'follow' no válido. Uso: follow @origen @destino");
                }
            } else if (comando.toLowerCase().startsWith("post ")) {
                String[] parts = comando.split(" ", 3);
                if (parts.length == 3) {
                    String origen = parts[1].substring(1); // Remove the '@'
                    String mensaje = parts[2];
                    handlePostCommand(origen, mensaje);
                } else {
                    System.out.println("Comando 'post' no válido. Uso: post @Origen mensaje compuesto");
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
    private void handleFollowCommand(String origen, String destino) {
        System.out.println("Ejecutando follow de " + origen + " a " + destino);

    }

    private void handlePostCommand(String origen, String mensaje) {
        System.out.println("Ejecutando post de " + origen + ": " + mensaje);
    }

    private void handleWallCommand(String destino) {
        // Implementar lógica para el comando 'wall'
        System.out.println("Mostrando wall de " + destino);
        // Aquí iría la lógica para mostrar el wall
    }
}