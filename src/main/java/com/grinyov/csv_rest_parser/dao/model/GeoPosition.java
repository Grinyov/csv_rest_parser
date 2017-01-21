package com.grinyov.csv_rest_parser.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by vgrinyov.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoPosition {
    private double latitude;
    private double longitude;
}
