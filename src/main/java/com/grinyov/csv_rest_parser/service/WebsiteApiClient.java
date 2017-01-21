package com.grinyov.csv_rest_parser.service;

import com.grinyov.csv_rest_parser.dao.model.Suggestion;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by vgrinyov.
 */
@Component
public class WebsiteApiClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${application.suggestionUrlTemplate}")
    private String suggestionUrl;

    public List<Suggestion> findSuggestionsByCity(@NonNull String city){

        restTemplate.getForEntity(suggestionUrl, Suggestion[].class,  )
        return null;
    }
}
