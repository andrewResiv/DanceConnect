package com.andrew.danceconnect.DanceConnect.service;

import com.andrew.danceconnect.DanceConnect.model.dto.EventDTO;

import com.andrew.danceconnect.DanceConnect.model.entity.Event;
import com.andrew.danceconnect.DanceConnect.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public List<EventDTO> getAllEvents(){
        return eventRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EventDTO getEventById(Long id){
        return convertToDTO(eventRepository.findById(id).orElse(null));
    }

    @Transactional
    public EventDTO createEvent(Event event){
        return convertToDTO(eventRepository.save(event));
    }

    @Transactional
    public EventDTO updateEvent(Long id, Event event){
        Event newEvent = eventRepository.findById(id).orElse(null);
        if(newEvent != null){
            modelMapper.map(newEvent, event);
            return convertToDTO(eventRepository.save(event));
        }
        return null;
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
