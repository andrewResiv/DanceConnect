package com.andrew.danceconnect.DanceConnect.service;

import com.andrew.danceconnect.DanceConnect.model.dto.UserDTO;
import com.andrew.danceconnect.DanceConnect.model.entity.User;
import com.andrew.danceconnect.DanceConnect.repository.UserRepository;
import com.andrew.danceconnect.DanceConnect.security.CustomUserDetails;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
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
    public void updateUser(Long id, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            modelMapper.map(userDTO, existingUser);
            userRepository.save(existingUser);
        }else{
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
    }

    public UserDTO convertUserToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
    public User convertDTOToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            return new CustomUserDetails(userOptional.get());
        }
        throw new UsernameNotFoundException("Username " + username + " not found");
    }
}
