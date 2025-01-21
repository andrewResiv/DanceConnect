package com.andrew.danceconnect.DanceConnect.service;

import com.andrew.danceconnect.DanceConnect.model.dto.DancePartnerRequestDTO;
import com.andrew.danceconnect.DanceConnect.model.entity.DancePartnerRequest;
import com.andrew.danceconnect.DanceConnect.model.entity.User;
import com.andrew.danceconnect.DanceConnect.repository.DancePartnerRequestRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DancePartnerRequestService {

    private final DancePartnerRequestRepository dancePartnerRequestRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public List<DancePartnerRequestDTO> getDancePartnerRequests() {
        return dancePartnerRequestRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void createRequest(DancePartnerRequestDTO dancePartnerRequestDTO) {
        Long userId = dancePartnerRequestDTO.getUserId();
        User user = userService.findUserById(userId);  // Получаем пользователя (управляемую сущность)

        // Преобразуем DTO в сущность
        DancePartnerRequest request = convertToEntity(dancePartnerRequestDTO);

        // Добавляем запрос к пользователю, если он еще не добавлен
        user.addDancePartnerRequest(request);
    }

    public DancePartnerRequestDTO getDancePartnerRequest(Long id) {
        DancePartnerRequest dancePartnerRequest = dancePartnerRequestRepository.findById(id).orElse(null);
        return convertToDTO(dancePartnerRequest);
    }

    @Transactional
    public DancePartnerRequestDTO update(Long id, DancePartnerRequest dancePartnerRequest) {
        DancePartnerRequest dancePartnerRequestUpdated = dancePartnerRequestRepository.findById(id).orElse(null);
        modelMapper.map(dancePartnerRequest, dancePartnerRequestUpdated);
        assert dancePartnerRequestUpdated != null;
        return convertToDTO(dancePartnerRequestRepository.save(dancePartnerRequestUpdated));
    }

    @Transactional
    public void delete(Long id) {
        dancePartnerRequestRepository.deleteById(id);
    }

    @Transactional
    public void assignPartner(DancePartnerRequestDTO requestDTO, Long partnerId) {
        DancePartnerRequest request = convertToEntity(requestDTO);
        request.setPartner( userService.findUserById(partnerId));
        dancePartnerRequestRepository.save(request);
    }

    //Преобразование из Entity в DTO
    public DancePartnerRequestDTO convertToDTO(DancePartnerRequest dancePartnerRequest) {
        DancePartnerRequestDTO dto = modelMapper.map(dancePartnerRequest, DancePartnerRequestDTO.class);
        User user = dancePartnerRequest.getUser();
        if (user != null) {
            dto.setUserId(user.getId()); // Заполняем только userId, если хотите оставить только ID
        }
        return dto;
    }
    //Преобразование из DTO в Entity
    public DancePartnerRequest convertToEntity(DancePartnerRequestDTO dancePartnerRequestDTO) {
        DancePartnerRequest dancePartnerRequest = modelMapper.map(dancePartnerRequestDTO, DancePartnerRequest.class);
        User user = userService.findUserById(dancePartnerRequestDTO.getUserId());
        dancePartnerRequest.setUser(user);
        dancePartnerRequest.setId(null);

        return dancePartnerRequest;
    }
}
