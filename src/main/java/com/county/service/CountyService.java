package com.county.service;

import com.county.entity.County;
import com.county.entity.dto.CountyDTO;
import com.county.repository.CountyRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountyService {
    private final CountyRepository repo;
    private static final int LIMIT = 5;

    public CountyService(CountyRepository repo) {
        this.repo = repo;
    }

    @Cacheable(value = "suggestions", key = "#q.trim().toLowerCase()")
    public List<CountyDTO> suggest(String q) {

        String cleaned = q.trim();
        if (cleaned.isEmpty()) return List.of();

        List<County> results;

        if (cleaned.contains(",")) {
            String[] parts = cleaned.split(",", 2);
            String namePart = parts[0].trim();
            String statePart = parts.length > 1 ? parts[1].trim() : "";
            results = repo.findByNameAndStateFragments(namePart, statePart);
        } else if (cleaned.length() <= 2) {
            results = repo.findByStateFragment(cleaned);
        } else {
            results = repo.findByNameFragment(cleaned);
            if (results.isEmpty())
                results = repo.findByStateFragment(cleaned);
        }

        String qLower = cleaned.toLowerCase();

        // Sort: prefix matches first → contains → alphabetical fallback
        List<County> sorted = results.stream()
                .sorted(Comparator
                        .comparing((County c) -> !c.getName().toLowerCase().startsWith(qLower))
                        .thenComparing(c -> !c.getName().toLowerCase().contains(qLower))
                        .thenComparing(County::getState)
                        .thenComparing(County::getName)
                )
                .limit(LIMIT)
                .toList();

        return sorted.stream().map(CountyDTO::fromEntity).collect(Collectors.toList());
    }
}