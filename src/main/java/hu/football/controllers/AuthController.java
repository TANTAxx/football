package hu.football.controllers;

import hu.football.models.dto.users.UserDto;
import hu.football.models.entities.users.User;
import hu.football.services.users.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<User> registration() {
        return null;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDTO) {
        UserDto save = userService.saveUser(userDTO);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }
}
