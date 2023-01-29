package com.cardx.Cardx.Services;

import com.cardx.Cardx.Model.Response.QuoteApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.Serializable;
import java.util.*;

@Component
@Service
public class QuoteApiService {

    @Autowired
    RestTemplate restTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    private int FIRST_ELEMENT = 0;

    private String URL = "https://type.fit/api/quotes";

    private Random rand = new Random();

    public static final String Cardx_KEY = "cardx-key";

    @Cacheable( // Cacheable help to store response of this method as cache with define key
            value = "quoteCache",
            key = "#root.target.Cardx_KEY"
    )
    public QuoteApiResponse getQuoteOfTheDay() throws JsonProcessingException {

        QuoteApiResponse quoteApiResponse = new QuoteApiResponse();

        // Headers for Api call : Quotes
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<>(headers);

        // Get response from 3rd party Apis
        String response =  restTemplate.exchange(URL, HttpMethod.GET, entity, String.class).getBody();

        // Getting array in json repsonse
        QuoteApiResponse[] langs = objectMapper.readValue(response, QuoteApiResponse[].class);
        List<QuoteApiResponse> langList = new ArrayList(Arrays.asList(langs)); // converted into List from Array

        int indexNum = RandomNumberGenerator(langList.size()); // Generating random number

        // set into response with random index from list
        quoteApiResponse.setText(langList.get(indexNum).getText());
        quoteApiResponse.setAuthor(langList.get(indexNum).getAuthor());

        return quoteApiResponse;
    }

    private int RandomNumberGenerator(int sizeOfList){

        int upperbound = sizeOfList;
        int randomNumber = rand.nextInt(upperbound);
        return randomNumber;
    }


}
