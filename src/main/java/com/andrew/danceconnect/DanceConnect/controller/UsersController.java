package com.andrew.danceconnect.DanceConnect.controller;

import com.andrew.danceconnect.DanceConnect.model.dto.UserDTO;
import com.andrew.danceconnect.DanceConnect.model.entity.User;
import com.andrew.danceconnect.DanceConnect.service.UserService;
import com.andrew.danceconnect.DanceConnect.util.CheckingBindingResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    private final CheckingBindingResult checkingBindingResult;

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
