package com.photostudio.serviceusers.service;

import com.photostudio.serviceusers.repo.UsersRepo;
import com.photostudio.serviceusers.repo.model.Users;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class UsersService {

    private final UsersRepo usersRepo;

    public List<Users> fetchAll(){
        return usersRepo.findAll();
    }

    public Users fetchById(long id) throws IllegalArgumentException{
        final Optional<Users> maybeUsers = usersRepo.findById(id);

        if (maybeUsers.isEmpty()) throw new IllegalArgumentException("Users not found");
        else return maybeUsers.get();
    }

    public long create(String firstName, String lastName, String phone, String login, String password){
        final Users users = new Users(firstName, lastName, phone, login, password);
        final Users savedUsers = usersRepo.save(users);

        return savedUsers.getId();
    }

    public void update(long id, String firstName, String lastName, String phone, String login, String password) throws IllegalArgumentException{
        final Optional<Users> maybeUsers = usersRepo.findById(id);

        if (maybeUsers.isEmpty()) throw new IllegalArgumentException("Users not found");
        final Users users = maybeUsers.get();
        if (firstName != null && !firstName.isBlank()) users.setFirstName(firstName);
        if (lastName != null && !lastName.isBlank()) users.setLastName(lastName);
        if (phone != null && !phone.isBlank()) users.setPhone(phone);
        if (firstName != null && !firstName.isBlank()) users.setFirstName(firstName);
        if (login != null && !login.isBlank()) users.setLogin(login);
        if (password != null && !password.isBlank()) users.setPassword(password);
        usersRepo.save(users);

    }

    public void delete(long id){
        usersRepo.deleteById(id);
    }
}
