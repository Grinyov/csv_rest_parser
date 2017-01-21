package com.grinyov.csv_rest_parser.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by vgrinyov.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsvSuggestionDto {

    /*
    use annotation JsonProperty to match name convention: "id" instead "_id"
     */
    @JsonProperty("_id")
    private long id;
    private String name;
    private String type;
    private double latitude;
    private double longitude;
}
