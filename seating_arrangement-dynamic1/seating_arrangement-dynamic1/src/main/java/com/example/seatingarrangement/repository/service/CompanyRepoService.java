package com.example.seatingarrangement.repository.service;


import com.example.seatingarrangement.dto.GetLayoutDto;
import com.example.seatingarrangement.entity.Company;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CompanyRepoService {
    void insert(Company company);

    Optional<Company> findByCompanyName(String companyName);

    GetLayoutDto findByLayoutId(String layoutId);
}
