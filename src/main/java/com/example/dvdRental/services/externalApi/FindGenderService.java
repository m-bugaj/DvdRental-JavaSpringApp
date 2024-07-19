package com.example.dvdRental.services.externalApi;

import com.example.dvdRental.exceptions.ExternalApiException;
import com.example.dvdRental.util.responses.FindGenderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FindGenderService {

    @Autowired
    private final RestTemplate restTemplate;

    @Value("${externalApi.findGender.url}")
    private String findGenderUrl;

    public FindGenderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FindGenderResponse findGender(String firstName) throws ExternalApiException {
        try {
            String url = findGenderUrl + firstName;
            ResponseEntity<FindGenderResponse> response = restTemplate.getForEntity(url, FindGenderResponse.class);
            return response.getBody();
        } catch (Exception e) {
            String url = findGenderUrl + firstName;
            throw new ExternalApiException("Find Gender", url);
        }

    }
}
