package com.andrew.danceconnect.DanceConnect.service;

import com.andrew.danceconnect.DanceConnect.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {
    private RoleRepository roleRepository;


}
