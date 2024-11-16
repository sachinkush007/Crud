package net.operation.crud.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<String> handleInvalidEnumValueException(InvalidFormatException ex) {
        if (ex.getTargetType().isEnum()) {
            String fieldName = ex.getPath().get(0).getFieldName();
            Class<?> enumType = ex.getTargetType();
            Object[] enumValues = enumType.getEnumConstants();
            String acceptedValues = String.join(", ",
                    Arrays.stream(enumValues)
                            .map(Object::toString)
                            .collect(Collectors.toList()));

            String errorMessage = String.format(
                    "Invalid value '%s' for field '%s'. Accepted values are: [%s].",
                    ex.getValue(), fieldName, acceptedValues
            );
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Invalid input provided.", HttpStatus.BAD_REQUEST);
    }
}
