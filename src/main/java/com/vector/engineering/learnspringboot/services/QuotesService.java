package com.vector.engineering.learnspringboot.services;

import com.vector.engineering.learnspringboot.cache.AppCache;
import com.vector.engineering.learnspringboot.dto.QuoteResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class QuotesService {

    @Value("${QUOTE_API_KEY}")
    private String API_KEY;

//    Now this is coming from the DB (@PostConstruct)
//    @Value("${QUOTE_API_URL}")
//    private String API;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    public QuoteResponseDto getQuote() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", API_KEY);
        HttpEntity<String> entity = new HttpEntity<>(headers);


        ResponseEntity<QuoteResponseDto[]> response  = restTemplate.exchange(appCache.appCacheMap.get(AppCache.KEYS.quotes_api.toString()), HttpMethod.GET, entity, QuoteResponseDto[].class);
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
