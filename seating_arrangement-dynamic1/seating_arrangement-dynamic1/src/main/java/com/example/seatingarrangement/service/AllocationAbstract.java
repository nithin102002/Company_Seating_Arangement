package com.example.seatingarrangement.service;

import com.example.seatingarrangement.dto.ResponseDto;
import com.example.seatingarrangement.dto.TeamObjectDto;
import com.example.seatingarrangement.repository.AllocationRepository;
import com.example.seatingarrangement.repository.TeamRepository;
import com.example.seatingarrangement.repository.service.AllocationRepoService;
import com.example.seatingarrangement.repository.service.CompanyRepoService;
import com.example.seatingarrangement.repository.service.TeamRepoService;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public abstract class AllocationAbstract {
    protected TeamRepoService teamRepoService;
    protected CompanyRepoService companyRepoService;
    protected TeamRepository teamRepository;
    protected AllocationRepoService allocationRepoService;
    protected AllocationRepository allocationRepository;
    protected ModelMapper modelMapper;

    public AllocationAbstract(TeamRepoService teamRepoService, CompanyRepoService companyRepoService, TeamRepository teamRepository, AllocationRepoService allocationRepoService, AllocationRepository allocationRepository, ModelMapper modelMapper) {
        this.teamRepoService = teamRepoService;
        this.companyRepoService = companyRepoService;
        this.teamRepository = teamRepository;
        this.allocationRepoService = allocationRepoService;
        this.allocationRepository = allocationRepository;
        this.modelMapper = modelMapper;
    }

    protected abstract ResponseEntity<ResponseDto> createAllocation(TeamObjectDto teamObjectDto) throws BadRequestException;
}
