package com.grinyov.csv_rest_parser;

import lombok.NonNull;
import org.springframework.boot.ExitCodeGenerator;
/**
 * Created by vgrinyov.
 */
public class ExitException extends RuntimeException implements ExitCodeGenerator {
    @Override
    public int getExitCode() {
        return 1;
    }

    public ExitException() {
        super();
    }

    public ExitException(Exception source) {
        super(source);
    }

    public ExitException(@NonNull String message) {
        super(message);
    }
}
