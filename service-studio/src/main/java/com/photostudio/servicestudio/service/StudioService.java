package com.photostudio.servicestudio.service;


import com.photostudio.servicestudio.repo.StudioRepo;
import com.photostudio.servicestudio.repo.model.Studio;
import com.photostudio.servicestudio.repo.StudioRepo;
import com.photostudio.servicestudio.repo.model.Studio;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class StudioService {

    private final StudioRepo studioRepo;

    public List<Studio> fetchAll(){
        return studioRepo.findAll();
    }

    public Studio fetchById(long id) throws IllegalArgumentException{
        final Optional<Studio> maybeStudio = studioRepo.findById(id);

        if (maybeStudio.isEmpty()) throw new IllegalArgumentException("Studio not found");
        else return maybeStudio.get();
    }

    public long create(String studioName, Integer price){
        final Studio studio = new Studio(studioName, price);
        final Studio savedStudio = studioRepo.save(studio);

        return savedStudio.getId();
    }

    public void update(long id, String studioName, Integer price) throws IllegalArgumentException{
        final Optional<Studio> maybeStudio = studioRepo.findById(id);

        if (maybeStudio.isEmpty()) throw new IllegalArgumentException("Studio not found");
        final Studio studio = maybeStudio.get();
        if (studioName != null && !studioName.isBlank()) studio.setStudioName(studioName);
        studioRepo.save(studio);

    }

    public void delete(long id){
        studioRepo.deleteById(id);
    }
}
