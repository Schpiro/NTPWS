package com.NTPWS.projekt.controller;

import com.NTPWS.projekt.model.dto.MemeDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("meme")
@CrossOrigin(origins = "https://localhost:4200")
public class MemeController {
    private final RestTemplate restTemplate;

    public MemeController() {
        this.restTemplate = new RestTemplate();
    }

    @GetMapping
    public MemeDTO getMeme() {
        MemeDTO meme = restTemplate.getForObject("https://meme-api.com/gimme/" , MemeDTO.class);
        return meme;
    }
}
