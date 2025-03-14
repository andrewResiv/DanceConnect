package com.andrew.danceconnect.DanceConnect.service;

import com.andrew.danceconnect.DanceConnect.model.dto.RegisterRequest;
import com.andrew.danceconnect.DanceConnect.model.dto.UserDTO;
import com.andrew.danceconnect.DanceConnect.model.entity.Role;
import com.andrew.danceconnect.DanceConnect.model.entity.User;
import com.andrew.danceconnect.DanceConnect.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Подключаем Mockito
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleService roleService;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDTO userDTO;
    private RegisterRequest registerRequest;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");

        userDTO = new UserDTO();
        userDTO.setUsername("testuser");
        userDTO.setEmail("test@example.com");

        registerRequest = new RegisterRequest();
        registerRequest.setUsername("newuser");
        registerRequest.setEmail("new@example.com");
        registerRequest.setPassword("password");
    }

    @Test
    void getUsers_ShouldReturnPageOfUsers() {
        Page<User> userPage = new PageImpl<>(List.of(user));
        when(userRepository.findAll(any(PageRequest.class))).thenReturn(userPage);
        when(modelMapper.map(any(User.class), eq(UserDTO.class))).thenReturn(userDTO);

        Page<UserDTO> result = userService.getUsers(PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(userRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void getUser_ShouldReturnUserDTO_WhenUserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        UserDTO result = userService.getUser(1L);

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void findUserByName_ShouldReturnUser_WhenUserExists() {
        // Подготавливаем мок
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        // Вызываем тестируемый метод
        User result = userService.findUserByName("testuser");

        // Проверяем результат
        assertNotNull(result);
        assertEquals(user, result);
        assertEquals("testuser", result.getUsername());

        // Проверяем, что метод findByUsername был вызван ровно 1 раз
        verify(userRepository, times(1)).findByUsername("testuser");
    }

    @Test
    void findUserByName_ShouldThrowException_WhenUserNotFound() {
        // Подготавливаем мок, который возвращает пустой Optional
        when(userRepository.findByUsername("testuser1")).thenReturn(Optional.empty());

        // Проверяем, что вызывается исключение
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> userService.findUserByName("testuser1"));

        assertEquals("User with name testuser1 not found", exception.getMessage());

        // Проверяем, что метод findByUsername был вызван ровно 1 раз
        verify(userRepository, times(1)).findByUsername("testuser1");
    }

    @Test
    void getUser_ShouldThrowException_WhenUserNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> userService.getUser(99L));
        assertEquals("User with id 99 not found", exception.getMessage());
    }

    @Test
    void registerUser_ShouldCreateNewUser() {
        when(userRepository.existsByUsername("newuser")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(roleService.findByName("ROLE_USER")).thenReturn(Optional.of(new Role()));

        User newUser = new User();
        newUser.setUsername("newuser");
        newUser.setEmail("new@example.com");
        newUser.setPassword("encodedPassword");

        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User result = userService.registerUser(registerRequest);

        assertNotNull(result);
        assertEquals("newuser", result.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void updateUser_ShouldUpdateExistingUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(modelMapper).map(userDTO, user);
        when(userRepository.save(user)).thenReturn(user);

        userService.updateUser(1L, userDTO);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void deleteUser_ShouldRemoveUser_WhenUserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void deleteUser_ShouldDoNothing_WhenUserNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> userService.deleteUser(99L));

        verify(userRepository, times(1)).findById(99L);
        verify(userRepository, never()).delete(any(User.class));
    }
}
