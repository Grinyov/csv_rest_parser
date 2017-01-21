package com.grinyov.csv_rest_parser;

import com.grinyov.csv_rest_parser.service.CsvSuggestionConverter;
import com.grinyov.csv_rest_parser.service.CsvSuggestionWriter;
import com.grinyov.csv_rest_parser.service.WebsiteApiClient;
import com.google.common.collect.ImmutableList;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.Collection;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

/**
 * Created by vgrinyov.
 */

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private CsvSuggestionWriter csvSuggestionWriter;
    @Autowired
    private WebsiteApiClient websiteApiClient;
    @Autowired
    private CsvSuggestionConverter csvSuggestionConverter;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build()));
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .bannerMode(Banner.Mode.LOG)
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {

        if (args.length != 1) {
            showHelpScreen();
            throw new ExitException(String.format("Program arguments were not correct: %s", Arrays.asList(args)));
        }

        try {
            final String cityName = args[0].trim();
            final String filename = cityName + ".csv";

            csvSuggestionWriter.write(
                    filename,
                    websiteApiClient.findSuggestionsByCity(cityName).stream()
                            .map(csvSuggestionConverter::toCsvSuggestionDto)
                            .collect(collectingAndThen(toList(), ImmutableList::copyOf)));

            System.out.println(String.format("Suggestions were sucessefully saved into %s", filename));

        } catch (RuntimeException e) {
            System.err.println("Error occured while performing your request. Check log for details");
            throw new ExitException(e);
        }
    }

    private void showHelpScreen() {
        System.out.println("Command line syntax is wrong! Please provide correct parameters!");
        System.out.println("Syntax: java -jar csv_rest_parser.jar \"CITY_NAME\"");
        System.out.println("Example: java -jar csv_rest_parser.jar berlin");
    }
}
