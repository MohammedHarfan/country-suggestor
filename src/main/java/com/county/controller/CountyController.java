package com.county.controller;

import com.county.entity.dto.CountyDTO;
import com.county.service.CountyService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CountyController {
    private final CountyService service;

    public CountyController(CountyService service) {
        this.service = service;
    }

    @GetMapping("/suggest")
    public List<CountyDTO> suggest(
            @RequestParam("q")
            @NotBlank(message = "q parameter is required and must not be blank")
            String q
    ) {
        return service.suggest(q);
    }
}