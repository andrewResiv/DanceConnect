package com.andrew.danceconnect.DanceConnect.service;

import com.andrew.danceconnect.DanceConnect.model.dto.UserDTO;

import com.andrew.danceconnect.DanceConnect.model.entity.User;
import com.andrew.danceconnect.DanceConnect.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public Page<UserDTO> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(this::convertUserToDTO);
    }

    public UserDTO getUser(Long id) {
        return convertUserToDTO(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found")));
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    @Transactional
    public UserDTO createUser(User user) {
        return convertUserToDTO(userRepository.save(user));
    }

    public UserDTO convertUserToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
    public User convertDTOToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }


}
