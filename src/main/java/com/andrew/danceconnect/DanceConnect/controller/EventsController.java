package com.andrew.danceconnect.DanceConnect.controller;

import com.andrew.danceconnect.DanceConnect.model.dto.EventDTO;
import com.andrew.danceconnect.DanceConnect.model.dto.UserDTO;
import com.andrew.danceconnect.DanceConnect.service.EventService;
import com.andrew.danceconnect.DanceConnect.service.RegistrationService;
import com.andrew.danceconnect.DanceConnect.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventsController {

    private final EventService eventService;
    private final RegistrationService registrationService;
    private final UserService userService;


    @GetMapping()
    public Page<EventDTO> getAllEvents(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return eventService.getAllEvents(pageable);
    }

    @GetMapping("/{id}")
    public EventDTO getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> createEvent(@RequestBody @Valid EventDTO eventDTO) {
        //Сохраним событие
        eventService.createEvent(eventDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateEvent(@PathVariable Long id, @RequestBody @Valid EventDTO eventDTO) {
        eventService.updateEvent(id, eventService.convertToEntity(eventDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}/registrationOnEvent/")
    public ResponseEntity<HttpStatus> registerUserOnEvent(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) {
        registrationService.registerUserOnEvent(userDTO, eventService.convertToEntity(eventService.getEventById(id)));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}/deleteUserFromEvent/{user_id}")
    public ResponseEntity<HttpStatus> deleteUserFromEvent(@PathVariable Long id, @PathVariable Long user_id) {
        registrationService.unregisterUserFromEvent(userService.findUserById(user_id), eventService.convertToEntity(eventService.getEventById(id)));
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
