package com.company.automation.advice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class GlobalExceptionHandlerTest {

    private static final String FIELD_NAME = "field";
    private static final String DEFAULT_MESSAGE = "defaultMessage";
    private static final String OBJECT_NAME = "objectName";
    private static final String GENERIC_ERROR_MESSAGE = "Generic error";

    @SuppressWarnings("null")
    @Test
    void testHandleValidationExceptions() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        mockFieldErrors(exception);

        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<Map<String, String>> response = handler.handleValidationExceptions(exception);

        assertValidationExceptionResponse(response);
    }

    @Test
    void testHandleGenericException() {
        Exception exception = new Exception(GENERIC_ERROR_MESSAGE);
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<String> response = handler.handleGenericException(exception);

        assertGenericExceptionResponse(response);
    }

    private void mockFieldErrors(MethodArgumentNotValidException exception) {
        when(exception.getBindingResult().getFieldErrors()).thenReturn(List.of(
                new FieldError(OBJECT_NAME, GlobalExceptionHandlerTest.FIELD_NAME, GlobalExceptionHandlerTest.DEFAULT_MESSAGE)
        ));
    }

    private void assertValidationExceptionResponse(ResponseEntity<Map<String, String>> response) {
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(GlobalExceptionHandlerTest.DEFAULT_MESSAGE, response.getBody().get(GlobalExceptionHandlerTest.FIELD_NAME));
    }

    private void assertGenericExceptionResponse(ResponseEntity<String> response) {
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(GlobalExceptionHandlerTest.GENERIC_ERROR_MESSAGE, response.getBody());
    }
}