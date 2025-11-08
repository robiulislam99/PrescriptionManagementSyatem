package com.cmed.prescription.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1")
public class ExternalApiController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/drug-interactions")
    public String getDrugInteractions() {
        String apiUrl = "https://rxnav.nlm.nih.gov/REST/interaction/interaction.json?rxcui=341248";
        return restTemplate.getForObject(apiUrl, String.class);
    }
}