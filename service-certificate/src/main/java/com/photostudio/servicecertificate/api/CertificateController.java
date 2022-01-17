package com.photostudio.servicecertificate.api;

import com.photostudio.servicecertificate.service.CertificateService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/certificate")
public final class CertificateController {

    private final CertificateService certificateService;

    @GetMapping
    public ResponseEntity<List<com.photostudio.servicecertificate.repo.model.Certificate>> index() {
        final List<com.photostudio.servicecertificate.repo.model.Certificate> certificate = certificateService.fetchAll();
        return ResponseEntity.ok(certificate);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.photostudio.servicecertificate.repo.model.Certificate> show(@PathVariable long id){
        try {
            final com.photostudio.servicecertificate.repo.model.Certificate certificate = certificateService.fetchById(id);
            return ResponseEntity.ok(certificate);
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody com.photostudio.servicecertificate.api.dto.Certificate certificate) {
        final Integer price = certificate.getPrice();
        final String user_login = certificate.getUser_login();
        final String studioName = certificate.getStudioName();
        final long id = certificateService.create(price, user_login, studioName);
        final String location = String.format("/certificate%d", id);

        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody com.photostudio.servicecertificate.api.dto.Certificate certificate){
        final Integer price = certificate.getPrice();
        final String user_login = certificate.getUser_login();
        final String studioName = certificate.getStudioName();
        try {
            certificateService.update(id, user_login, studioName);

            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        certificateService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
