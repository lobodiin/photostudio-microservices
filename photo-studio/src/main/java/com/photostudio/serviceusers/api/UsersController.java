package com.photostudio.serviceusers.api;

import com.photostudio.serviceusers.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public final class UsersController {

    private final UsersService usersService;

    @GetMapping
    public ResponseEntity<List<com.photostudio.serviceusers.repo.model.Users>> index() {
        final List<com.photostudio.serviceusers.repo.model.Users> users = usersService.fetchAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.photostudio.serviceusers.repo.model.Users> show(@PathVariable long id){
        try {
            final com.photostudio.serviceusers.repo.model.Users users = usersService.fetchById(id);
            return ResponseEntity.ok(users);
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody com.photostudio.serviceusers.api.dto.Users users) {
        final String firstName = users.getFirstName();
        final String lastName = users.getLastName();
        final String phone = users.getPhone();
        final String login = users.getLogin();
        final String password = users.getPassword();
        final long id = usersService.create(firstName, lastName, phone, login, password);
        final String location = String.format("/users%d", id);

        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody com.photostudio.serviceusers.api.dto.Users users){
        final String firstName = users.getFirstName();
        final String lastName = users.getLastName();
        final String phone = users.getPhone();
        final String login = users.getLogin();
        final String password = users.getPassword();
        try {
            usersService.update(id, firstName, lastName, phone, login, password);

            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        usersService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
