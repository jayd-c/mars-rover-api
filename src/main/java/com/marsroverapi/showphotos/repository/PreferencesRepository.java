package com.marsroverapi.showphotos.repository;

import com.marsroverapi.showphotos.dto.HomeDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferencesRepository extends JpaRepository<HomeDto, Long> {
}
