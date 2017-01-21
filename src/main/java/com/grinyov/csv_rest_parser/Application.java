package com.grinyov.csv_rest_parser;

import com.google.common.collect.ImmutableList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.util.stream.Collectors.toList;

/**
 * Created by vgrinyov.
 */

@SpringBootApplication
public class Application implements CommandLineRunner {

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

        csvSuggestWriter.write(fileName, someSiteApiClient.findSuggestionsByCity().stream()
                .map(csvSuggestionConverter::toCsvSuggestionDto)
                .collect(collectingAndThen(toList(), ImmutableList::copyOf)));

    }
}
