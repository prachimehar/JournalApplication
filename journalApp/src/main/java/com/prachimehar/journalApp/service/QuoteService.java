package com.prachimehar.journalApp.service;

import com.prachimehar.journalApp.api.response.QuoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteService {
    @Autowired
    private RestTemplate restTemplate;

    public QuoteResponse getQuote() {
        String url = "https://zenquotes.io/api/random";
        ResponseEntity<QuoteResponse[]> response = restTemplate.getForEntity(url, QuoteResponse[].class);

        QuoteResponse[] quotes = response.getBody();
        return (quotes != null && quotes.length > 0) ? quotes[0] : null;
    }
}
