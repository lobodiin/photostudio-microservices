package com.photostudio.servicecertificate.repo;

import com.photostudio.servicecertificate.repo.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepo extends JpaRepository<Certificate, Long> {
}
