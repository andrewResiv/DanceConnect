package com.andrew.danceconnect.DanceConnect.controllers;

import com.andrew.danceconnect.DanceConnect.dto.UserDTO;
import com.andrew.danceconnect.DanceConnect.models.User;
import com.andrew.danceconnect.DanceConnect.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO,
                                              BindingResult bindingResult) {
        User user = userService.convertDTOToUser(userDTO);
        // ЕСли есть ошибки валидации
        if (bindingResult.hasErrors()) {
            StringBuilder errorsMsg = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                errorsMsg.append(fieldError.getField())
                        .append(": ")
                        .append(fieldError.getDefaultMessage())
                        .append("\n");
            }

            // Ошибки с подробным сообщением
            throw new ValidationException(errorsMsg.toString());
        }
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }
}
