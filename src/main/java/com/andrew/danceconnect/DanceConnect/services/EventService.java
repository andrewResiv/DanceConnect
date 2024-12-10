package com.andrew.danceconnect.DanceConnect.services;

import com.andrew.danceconnect.DanceConnect.dto.EventDTO;

import com.andrew.danceconnect.DanceConnect.models.Event;
import com.andrew.danceconnect.DanceConnect.repositories.EventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EventService(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

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
