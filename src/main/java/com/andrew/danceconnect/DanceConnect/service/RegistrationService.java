package com.andrew.danceconnect.DanceConnect.service;

import com.andrew.danceconnect.DanceConnect.model.dto.UserDTO;
import com.andrew.danceconnect.DanceConnect.model.entity.Event;
import com.andrew.danceconnect.DanceConnect.model.entity.Registration;
import com.andrew.danceconnect.DanceConnect.model.entity.User;
import com.andrew.danceconnect.DanceConnect.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final UserService userService;

    public List<User> getUsersByEvent(Event event) {
        return registrationRepository.findUsersByEventId(event.getId());
    }

    public List<Event> getEventsByUser(User user) {
        return registrationRepository.findEventsByUserId(user.getId());
    }

    @Transactional
    public Registration registerUserOnEvent(UserDTO userDTO, Event event) {
        User user = userService.convertDTOToUser(userDTO);
        checkIfUserAlreadyRegistered(user, event);
        Registration registration = Registration.builder()
                .user(user)
                .event(event)
                .build();
        return registrationRepository.save(registration);
    }

    private void checkIfUserAlreadyRegistered(User user, Event event) {
        if (registrationRepository.existsByUserAndEvent(user, event)) {
            throw new IllegalStateException("User is already registered for the event.");
        }
    }
    @Transactional
    public void unregisterUserFromEvent(User user, Event event) {
        Registration registration = registrationRepository.findByUserAndEvent(user, event)
                .orElseThrow(() -> new IllegalArgumentException("User is not registered for the event."));
        registrationRepository.delete(registration);
    }

}
