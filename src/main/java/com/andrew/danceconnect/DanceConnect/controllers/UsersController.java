package com.andrew.danceconnect.DanceConnect.controllers;

import com.andrew.danceconnect.DanceConnect.dto.UserDTO;
import com.andrew.danceconnect.DanceConnect.models.User;
import com.andrew.danceconnect.DanceConnect.services.UserService;
import com.andrew.danceconnect.DanceConnect.util.CheckingBindingResult;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    private final CheckingBindingResult checkingBindingResult;

    @Autowired
    public UsersController(UserService userService, CheckingBindingResult checkingBindingResult) {
        this.userService = userService;
        this.checkingBindingResult = checkingBindingResult;
    }

    @GetMapping()
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid UserDTO userDTO,
                                              BindingResult bindingResult) {
        User user = userService.convertDTOToUser(userDTO);
        // ЕСли есть ошибки валидации
        checkingBindingResult.checkBindingResult(bindingResult);
        userService.createUser(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
