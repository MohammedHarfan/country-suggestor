package com.county.config;

import com.county.entity.County;
import com.county.repository.CountyRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.core.io.ClassPathResource;
import jakarta.transaction.Transactional;

import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    private final CountyRepository repo;
    private final ObjectMapper mapper = new ObjectMapper();

    public DataLoader(CountyRepository r) {
        this.repo = r;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var res = new ClassPathResource("data.json");
        try (InputStream is = res.getInputStream()) {
            List<CountyRecord> recs = mapper.readValue(is, new TypeReference<>() {
            });
            for (var r : recs) repo.save(new County(r.fips(), r.state(), r.name()));
        }
    }

    public static record CountyRecord(String fips, String state, String name) {
    }
}