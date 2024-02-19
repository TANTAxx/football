package hu.football.controllers;


import hu.football.models.entities.users.User;
import hu.football.services.users.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/users")
public class UserController {

    public final UserService userService;


    //GetAllUsers, DeleteUser/s Csak az admin tudja!

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/deleteUser")
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(User user) {
        userService.deleteUser(user);
    }
}
