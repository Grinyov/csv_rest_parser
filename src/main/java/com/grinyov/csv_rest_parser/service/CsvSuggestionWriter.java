package com.grinyov.csv_rest_parser.service;


import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by vgrinyov.
 */
@Component
public class CsvSuggestionWriter {

    /*
    Use annotation Lombock @NonNull instead checking on null
     */
    public void write(@NonNull String fileName, @NonNull List<CsvSuggestionDto> data){

    }
}
