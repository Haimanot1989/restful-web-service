package com.itsisterhood.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static {
        users.add(new User(1, "Adam", LocalDate.now()));
        users.add(new User(2, "Eve", LocalDate.now()));
        users.add(new User(3, "Jack", LocalDate.now()));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(user.getId() == null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public Optional<User> findOne(int id){
        return users.stream().filter(u -> u.getId() == id).findFirst();
    }
    public Optional<User> deleteById(int id) {
        Optional<User> user = users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();

        user.ifPresent(users::remove);


        return user;
    }
}
