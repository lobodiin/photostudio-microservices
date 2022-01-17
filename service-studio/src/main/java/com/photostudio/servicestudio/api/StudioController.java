package com.photostudio.servicestudio.api;

import com.photostudio.servicestudio.service.StudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/studio")
public final class StudioController {

    private final StudioService studioService;

    @GetMapping
    public ResponseEntity<List<com.photostudio.servicestudio.repo.model.Studio>> index() {
        final List<com.photostudio.servicestudio.repo.model.Studio> users = studioService.fetchAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.photostudio.servicestudio.repo.model.Studio> show(@PathVariable long id){
        try {
            final com.photostudio.servicestudio.repo.model.Studio studio = studioService.fetchById(id);
            return ResponseEntity.ok(studio);
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody com.photostudio.servicestudio.api.dto.Studio studio) {
        final String studioName = studio.getStudioName();
        final Integer price = studio.getPrice();
        final long id = studioService.create(studioName, price);
        final String location = String.format("/studio%d", id);

        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody com.photostudio.servicestudio.api.dto.Studio studio){
        final String studioName = studio.getStudioName();
        final Integer price = studio.getPrice();
        try {
            studioService.update(id, studioName, price);

            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        studioService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
