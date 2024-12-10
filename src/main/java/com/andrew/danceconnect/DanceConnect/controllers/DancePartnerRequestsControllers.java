package com.andrew.danceconnect.DanceConnect.controllers;

import com.andrew.danceconnect.DanceConnect.dto.DancePartnerRequestDTO;
import com.andrew.danceconnect.DanceConnect.services.DancePartnerRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class DancePartnerRequestsControllers {

    private final DancePartnerRequestService dancePartnerRequestService;

    @Autowired
    public DancePartnerRequestsControllers(DancePartnerRequestService dancePartnerRequestService) {
        this.dancePartnerRequestService = dancePartnerRequestService;
    }

    @GetMapping()
    public List<DancePartnerRequestDTO> getRequests() {
        return dancePartnerRequestService.getDancePartnerRequests();
    }

}
