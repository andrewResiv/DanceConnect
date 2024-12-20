package com.andrew.danceconnect.DanceConnect.controllers;

import com.andrew.danceconnect.DanceConnect.dto.EventDTO;
import com.andrew.danceconnect.DanceConnect.services.EventService;
import com.andrew.danceconnect.DanceConnect.util.CheckingBindingResult;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/events")
public class EventsController {

    private final EventService eventService;
    private final CheckingBindingResult checkingBindingResult;

    @Autowired
    public EventsController(EventService eventService, CheckingBindingResult checkingBindingResult) {
        this.eventService = eventService;
        this.checkingBindingResult = checkingBindingResult;
    }

    @GetMapping()
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventDTO getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> createEvent(@RequestBody @Valid EventDTO eventDTO,
                                                  BindingResult bindingResult) {
        // ЕСли есть ошибки валидации
        checkingBindingResult.checkBindingResult(bindingResult);
        //Преобразуем в entity и сохраним событие
        eventService.createEvent(eventService.convertToEntity(eventDTO));

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateEvent(@PathVariable Long id, @RequestBody @Valid EventDTO eventDTO) {
        eventService.updateEvent(id, eventService.convertToEntity(eventDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
