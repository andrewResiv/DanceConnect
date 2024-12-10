package com.andrew.danceconnect.DanceConnect.services;

import com.andrew.danceconnect.DanceConnect.dto.DancePartnerRequestDTO;
import com.andrew.danceconnect.DanceConnect.models.DancePartnerRequest;
import com.andrew.danceconnect.DanceConnect.repositories.DancePartnerRequestRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DancePartnerRequestService {

    private final DancePartnerRequestRepository dancePartnerRequestRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DancePartnerRequestService(DancePartnerRequestRepository dancePartnerRequestRepository, ModelMapper modelMapper) {
        this.dancePartnerRequestRepository = dancePartnerRequestRepository;
        this.modelMapper = modelMapper;
    }
    
    //create

    //update

    //delete

    public DancePartnerRequestDTO convertToDTO(DancePartnerRequest dancePartnerRequest) {
        return modelMapper.map(dancePartnerRequest, DancePartnerRequestDTO.class);
    }

    public DancePartnerRequest convertToEntity(DancePartnerRequestDTO dancePartnerRequestDTO) {
        return modelMapper.map(dancePartnerRequestDTO, DancePartnerRequest.class);
    }
}
