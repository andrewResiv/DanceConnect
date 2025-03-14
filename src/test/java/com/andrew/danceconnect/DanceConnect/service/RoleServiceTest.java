package com.andrew.danceconnect.DanceConnect.service;

import com.andrew.danceconnect.DanceConnect.model.entity.Role;
import com.andrew.danceconnect.DanceConnect.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class) // Подключаем Mockito
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    private Role role;

    @BeforeEach
    void setUp() {
        role = new Role();
        role.setId(1L);
        role.setName("test");
    }

    @Test
    void getRoleByName_ShouldReturnRoleWithCorrectName() {
        when(roleRepository.findByName("test")).thenReturn(Optional.of(role));

        Optional<Role> result = roleService.findByName("test");

        assertTrue(result.isPresent());
        assertEquals("test", result.get().getName());  // Проверяем имя
    }

    @Test
    void getRoleByName_ShouldReturnEmptyOptional() {
        // Мокируем отсутствие роли
        when(roleRepository.findByName("nonexistent")).thenReturn(Optional.empty());

        Optional<Role> result = roleService.findByName("nonexistent");

        // Проверяем, что результат пустой
        assertTrue(result.isEmpty());
    }

}