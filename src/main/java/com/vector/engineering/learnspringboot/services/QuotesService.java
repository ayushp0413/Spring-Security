package com.vector.engineering.learnspringboot.services;

import com.vector.engineering.learnspringboot.dto.QuoteResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class QuotesService {

    private static final String API_KEY = "bnx267wV6TNgdFfRkgKadw==mAQyU3p4tz4W0j44";
    private static final String API = "https://api.api-ninjas.com/v1/quotes/";

    @Autowired
    private RestTemplate restTemplate;

    public QuoteResponseDto getQuote() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", API_KEY);
        HttpEntity<String> entity = new HttpEntity<>(headers);


        ResponseEntity<QuoteResponseDto[]> response  = restTemplate.exchange(API, HttpMethod.GET, entity, QuoteResponseDto[].class);
        if(response.getStatusCode() == HttpStatus.OK && response.getBody() != null)
        {
            System.out.println(response.getBody());
            return response.getBody()[0];
        }
        else {
            throw new Exception("External Api issue "+ response.getStatusCode());
        }
    }
}
