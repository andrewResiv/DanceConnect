package com.andrew.danceconnect.DanceConnect.controllers;

import com.andrew.danceconnect.DanceConnect.dto.DancePartnerRequestDTO;
import com.andrew.danceconnect.DanceConnect.services.DancePartnerRequestService;
import com.andrew.danceconnect.DanceConnect.util.CheckingBindingResult;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/requests")
public class DancePartnerRequestsControllers{

    private final DancePartnerRequestService dancePartnerRequestService;

    @Autowired
    public DancePartnerRequestsControllers(DancePartnerRequestService dancePartnerRequestService) {
        this.dancePartnerRequestService = dancePartnerRequestService;
    }

    @GetMapping()
    public List<DancePartnerRequestDTO> getRequests() {
        return dancePartnerRequestService.getDancePartnerRequests();
    }

    @GetMapping("/{id}")
    public DancePartnerRequestDTO getRequest(@PathVariable Long id) {
        return dancePartnerRequestService.getDancePartnerRequest(id);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addRequest(@RequestBody @Valid DancePartnerRequestDTO dancePartnerRequestDTO,
                                                 BindingResult bindingResult) {
        CheckingBindingResult.checkBindingResult(bindingResult);
        dancePartnerRequestService.createRequest(dancePartnerRequestDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateRequest(@PathVariable Long id,
                                                    DancePartnerRequestDTO dancePartnerRequestDTO) {
        dancePartnerRequestService.update(id, dancePartnerRequestService.convertToEntity(dancePartnerRequestDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRequest(@PathVariable Long id) {
        dancePartnerRequestService.delete(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
