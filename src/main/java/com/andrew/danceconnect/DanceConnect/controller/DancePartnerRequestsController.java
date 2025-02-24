package com.andrew.danceconnect.DanceConnect.controller;

import com.andrew.danceconnect.DanceConnect.model.dto.DancePartnerRequestDTO;
import com.andrew.danceconnect.DanceConnect.service.DancePartnerRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/dance-partner-requests")
public class DancePartnerRequestsController {

    private final DancePartnerRequestService dancePartnerRequestService;

    @GetMapping()
    public List<DancePartnerRequestDTO> getRequests() {
        return dancePartnerRequestService.getDancePartnerRequests();
    }

    @GetMapping("/{id}")
    public DancePartnerRequestDTO getRequest(@PathVariable Long id) {
        return dancePartnerRequestService.getDancePartnerRequest(id);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addRequest(@RequestBody @Valid DancePartnerRequestDTO dancePartnerRequestDTO) {
        dancePartnerRequestService.createRequest(dancePartnerRequestDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/assignPartner")
    public ResponseEntity<HttpStatus> assignPartner(@PathVariable Long id,
                                                    @RequestBody @Valid DancePartnerRequestDTO dancePartnerRequestDTO) {
        dancePartnerRequestService.assignPartner(dancePartnerRequestDTO, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PatchMapping("/{id}/unassignPartner")
    public ResponseEntity<HttpStatus> unassignPartner(@PathVariable Long id,
                                                    @RequestBody @Valid DancePartnerRequestDTO dancePartnerRequestDTO) {
        dancePartnerRequestService.unassignPartner(dancePartnerRequestDTO, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
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
