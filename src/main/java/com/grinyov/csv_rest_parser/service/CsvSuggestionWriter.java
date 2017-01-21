package com.grinyov.csv_rest_parser.service;


import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.annotations.VisibleForTesting;
import com.grinyov.csv_rest_parser.service.dto.CsvSuggestionDto;
import lombok.Cleanup;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

/**
 * Created by vgrinyov.
 */
@Component
public class CsvSuggestionWriter {

    private CsvMapper csvMapper =  new CsvMapper();
    private CsvSchema schema = csvMapper
            .schemaFor(CsvSuggestionDto.class)
            .withHeader()
            .sortedBy("_id", "name", "type", "latitude", "longitude");

    /*
    Use Lombok's annotation @NonNull instead checking on null
    and annotation @Cleanup - ensure a given resource is automatically cleaned up
    before the code execution path exits your current scope/
     */
    public void write(@NonNull String fileName, @NonNull List<CsvSuggestionDto> data){
        try {
            @Cleanup Writer writer = new PrintWriter(new FileWriter(fileName), true);
            doWrite(writer, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    Use helper's from jackson-dataformat
     */
    @VisibleForTesting
    protected void doWrite(@NonNull Writer writer, @NonNull List<CsvSuggestionDto> data) throws IOException{
        csvMapper.writer().with(schema).writeValues(writer).writeAll(data);
    }
}
