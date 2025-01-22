package com.andrew.danceconnect.DanceConnect.service;

import com.andrew.danceconnect.DanceConnect.model.dto.EventDTO;

import com.andrew.danceconnect.DanceConnect.model.entity.Event;
import com.andrew.danceconnect.DanceConnect.model.entity.User;
import com.andrew.danceconnect.DanceConnect.repository.EventRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final EntityManager entityManager;

    public List<EventDTO> getAllEvents(){
        return eventRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EventDTO getEventById(Long id){
        return convertToDTO(eventRepository.findById(id).orElse(null));
    }

    @Transactional
    public void createEvent(EventDTO eventDTO){
        // Проверяем, существует ли пользователь с указанным ID
        User creator = userService.findUserById(eventDTO.getCreator().getId());

        // Конвертируем DTO в сущность Event
        Event event = convertToEntity(eventDTO);
        event.setCreator(creator); // Устанавливаем пользователя как создателя события

        // Сохраняем событие в базе данных
        eventRepository.save(event);
    }

    @Transactional
    public EventDTO updateEvent(Long id, Event event){
        Event newEvent = eventRepository.findById(id).orElse(null);
        if (newEvent == null) {
            throw new EntityNotFoundException("Event with id " + id + " not found.");
        }
        modelMapper.map(event, newEvent);
        return convertToDTO(eventRepository.save(newEvent));
    }
    @Transactional
    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }

    public EventDTO convertToDTO(Event event){
        return modelMapper.map(event, EventDTO.class);
    }

    public Event convertToEntity(EventDTO eventDTO){
        return modelMapper.map(eventDTO, Event.class);
    }
}
