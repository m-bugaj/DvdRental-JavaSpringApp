package com.example.dvdRental.services.externalApi;

import com.example.dvdRental.util.responses.DisifyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DisifyService {

    @Autowired
    private final RestTemplate restTemplate;

    @Value("${externalApi.disify.url}")
    private String disifyUrl;

    public DisifyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public DisifyResponse checkEmail(String email) {
        String url = disifyUrl + email;
        ResponseEntity<DisifyResponse> response = restTemplate.getForEntity(url, DisifyResponse.class);
        return response.getBody();
    }
}
