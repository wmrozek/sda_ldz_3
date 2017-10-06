package com.sda.jspexample.users.repository;

import com.sda.jspexample.library.books.Book;
import com.sda.jspexample.model.User;
import com.sda.jspexample.random.utils.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static List<User> list = new ArrayList<>();

    public static void addUser(User user){
        user.setId(RandomNumberGenerator.getNextSequence());
        list.add(user);
    }

    public static List<User> getUsers(){
        return list;
    }

    public static User getUserById(String id){
        if (id == null){
            return null;
        }
        User result = list.stream()
                .filter(user -> Integer.toString(user.getId()).equals(id)).findAny().orElse(null);
        return result;

    }
}
