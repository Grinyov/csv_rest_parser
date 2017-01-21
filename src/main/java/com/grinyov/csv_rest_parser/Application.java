package com.grinyov.csv_rest_parser;

import com.google.common.collect.ImmutableList;
import com.grinyov.csv_rest_parser.service.CsvSuggestionConverter;
import com.grinyov.csv_rest_parser.service.CsvSuggestionWriter;
import com.grinyov.csv_rest_parser.service.WebsiteApiClient;
import lombok.AllArgsConstructor;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

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

        String cityName = args[0].trim();
        String fileName = cityName + ".csv";

        /*  Call csv writer.
           FIrstly, apiclient's method call and receive data in the form map.
           Call method csvSuggestionConverter transform to dto and
           move to new immutable collection.
           Finally, save to file in *.csv format
        */

        csvSuggestionWriter.write(fileName, websiteApiClient.findSuggestionsByCity(cityName).stream()
                .map(csvSuggestionConverter::toCsvSuggestionDto)
                .collect(collectingAndThen(toList(), ImmutableList::copyOf)));

    }
}
