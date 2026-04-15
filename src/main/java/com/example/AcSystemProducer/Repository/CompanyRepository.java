package com.example.AcSystemProducer.Repository;

import com.example.AcSystemProducer.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    boolean existsByName(String name);
    Optional<Company> findByName(String name);
    List<Company> findAll();
}
