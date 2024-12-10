package com.andrew.danceconnect.DanceConnect.services;

import com.andrew.danceconnect.DanceConnect.dto.DancePartnerRequestDTO;
import com.andrew.danceconnect.DanceConnect.models.DancePartnerRequest;
import com.andrew.danceconnect.DanceConnect.repositories.DancePartnerRequestRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DancePartnerRequestService {

    private final DancePartnerRequestRepository dancePartnerRequestRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DancePartnerRequestService(DancePartnerRequestRepository dancePartnerRequestRepository, ModelMapper modelMapper) {
        this.dancePartnerRequestRepository = dancePartnerRequestRepository;
        this.modelMapper = modelMapper;
    }

    public List<DancePartnerRequestDTO> getDancePartnerRequests() {
        return dancePartnerRequestRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DancePartnerRequestDTO createRequest(DancePartnerRequest dancePartnerRequest) {
        return convertToDTO(dancePartnerRequestRepository.save(dancePartnerRequest));
    }
    public DancePartnerRequestDTO getDancePartnerRequest(Long id) {
        DancePartnerRequest dancePartnerRequest = dancePartnerRequestRepository.findById(id).orElse(null);
        return convertToDTO(dancePartnerRequest);
    }

    public DancePartnerRequestDTO update(Long id, DancePartnerRequest dancePartnerRequest) {
        DancePartnerRequest dancePartnerRequestUpdated = dancePartnerRequestRepository.findById(id).orElse(null);
        modelMapper.map(dancePartnerRequest, dancePartnerRequestUpdated);
        assert dancePartnerRequestUpdated != null;
        return convertToDTO(dancePartnerRequestRepository.save(dancePartnerRequestUpdated));
    }
    public void delete(Long id) {
        dancePartnerRequestRepository.deleteById(id);
    }

    public DancePartnerRequestDTO convertToDTO(DancePartnerRequest dancePartnerRequest) {
        return modelMapper.map(dancePartnerRequest, DancePartnerRequestDTO.class);
    }

    public DancePartnerRequest convertToEntity(DancePartnerRequestDTO dancePartnerRequestDTO) {
        return modelMapper.map(dancePartnerRequestDTO, DancePartnerRequest.class);
    }
}
