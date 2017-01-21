package com.grinyov.csv_rest_parser.dao.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Created by vgrinyov.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Suggestion {
    @JsonProperty("_id")
    private long id;
    private String key;
    private String name;
    private String fullName;
    @JsonProperty("iata_airport_code")
    private String iataAirportCode;
    private String type;
    private String country;
    @JsonProperty("geo_position")
    private GeoPosition geoPosition;
    private long locationId;
    private boolean inEurope;
    private int countryId;
    //private CountryCode countryCode;
    private String countryCode;
    private boolean coreCountry;
    private long distance;
    private Map<String, String> names;
    private Map<String, String> alternativeNames;
}
