package com.photostudio.servicestudio.repo;

import com.photostudio.servicestudio.repo.model.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepo extends JpaRepository<Studio, Long> {
}
