package com.photostudio.servicecertificate.service;

import com.photostudio.servicecertificate.repo.CertificateRepo;
import com.photostudio.servicecertificate.repo.model.Certificate;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class CertificateService {

    private final CertificateRepo certificateRepo;

    public List<Certificate> fetchAll(){
        return certificateRepo.findAll();
    }

    public Certificate fetchById(long id) throws IllegalArgumentException{
        final Optional<Certificate> maybeCertificate = certificateRepo.findById(id);

        if (maybeCertificate.isEmpty()) throw new IllegalArgumentException("Certificate not found");
        else return maybeCertificate.get();
    }

    public long create(Integer price, String user_login, String studioName){
        final Certificate certificate = new Certificate(price, user_login, studioName);
        final Certificate savedCertificate = certificateRepo.save(certificate);

        return savedCertificate.getId();
    }

    public void update(long id, String user_login, String studioName) throws IllegalArgumentException{
        final Optional<Certificate> maybeCertificate = certificateRepo.findById(id);

        if (maybeCertificate.isEmpty()) throw new IllegalArgumentException("Certificate not found");
        final Certificate certificate = maybeCertificate.get();
        if (user_login != null && !user_login.isBlank()) certificate.setUser_login(user_login);
        if (studioName != null && !studioName.isBlank()) certificate.setStudioName(studioName);

        certificateRepo.save(certificate);

    }

    public void delete(long id){
        certificateRepo.deleteById(id);
    }
}