package com.grinyov.csv_rest_parser.service;

import com.grinyov.csv_rest_parser.dao.model.Suggestion;
import com.grinyov.csv_rest_parser.service.dto.CsvSuggestionDto;
import lombok.NonNull;
import org.springframework.stereotype.Component;

/**
 * Created by vgrinyov.
 */
@Component
public class CsvSuggestionConverter {
    public CsvSuggestionDto toCsvSuggestionDto(@NonNull Suggestion input){
        CsvSuggestionDto dto = new CsvSuggestionDto();

        dto.setId(input.getId());
        dto.setName(input.getName());
        dto.setType(input.getType());
        dto.setLatitude(input.getGeoPosition().getLatitude());
        dto.setLongitude(input.getGeoPosition().getLongitude());

        return dto;
    }
}
