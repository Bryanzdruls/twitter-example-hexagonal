package org.example;

import org.example.usecases.GenerateUsersUseCase;
import org.example.user.User;
import org.example.usecases.ValidationsUseCase;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class EntryPoint {

    private final GenerateUsersUseCase generateUsersUseCase;

    private final ValidationsUseCase validationsUseCase;

    private final Repository repository;

    private static EntryPoint entryPoint;

    private EntryPoint(){
        generateUsersUseCase = new GenerateUsersUseCase();
        repository = Repository.repositoryInstance();
        validationsUseCase = new ValidationsUseCase();
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

        while (option != 4) {
            System.out.println("Ingrese algun comando:");
            System.out.println("1. follow @origin @destino");
            System.out.println("2. post @origen message compuesto");
            System.out.println("3. wall @destino");
            System.out.println("4. exit");
            System.out.print("Usuarios disponibles ");
            for (User user :
                    repository.getUsers()) {
                System.out.print(user.getUserTag().value() + ", ");
            }
            System.out.println();
            System.out.print("> ");
            String comando = scanner.nextLine();
            System.out.println("su comando fue: " + comando);
            validationsUseCase.execute(comando);
        }
        scanner.close();
    }
}