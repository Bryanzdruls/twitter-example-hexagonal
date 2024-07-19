package org.example;

import org.example.user.User;

import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Repository {
    private List<User> users = new ArrayList<>();
    private static Repository repository;
    private Repository(){
    };

    public static Repository repositoryInstance(){
        if (repository == null){
            repository = new Repository();
        }
        return repository;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}