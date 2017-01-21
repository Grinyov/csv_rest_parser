package com.grinyov.csv_rest_parser;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.util.stream.Collectors.toList;

/**
 * Created by vgrinyov.
 */

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private CsvSuggestionWriter csvSuggestionWriter;

    @Override
    public void run(String... args) throws Exception {

        String cityName = args[0].trim();
        String fileName = cityName + ".csv";

        /*  Call csv writer.
           FIrstly, apiclient's method call and receive data in the form map.
           Call method csvSuggestionConverter transform to dto and
           move to new immutable collection.
           Finally, save to file in *.csv format
        */

        csvSuggestionWriter.write(fileName, websiteApiClient.findSuggestionsByCity().stream()
                .map(csvSuggestionConverter::toCsvSuggestionDto)
                .collect(collectingAndThen(toList(), ImmutableList::copyOf)));

    }
}
