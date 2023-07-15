package com.festeban26.nttdata.modules.crypto_quotation.application.web.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebInputException;

@RestControllerAdvice
public class QuotationExceptionHandler {
    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<QuotationErrorResponse> handleInputException(ServerWebInputException ex) {
        // TODO EMFA
        return ResponseEntity.badRequest().body(new QuotationErrorResponse(400, ex.getMessage()));
        // Handle invalid input exceptions
    }

    @ExceptionHandler(QuotationException.class)
    public ResponseEntity<QuotationErrorResponse> handleQuotationException(QuotationException ex) {
        // TODO EMFA
        return ResponseEntity.badRequest().body(new QuotationErrorResponse(400, ex.getMessage()));
        // Handle custom quotation exceptions
    }

    // Add more exception handlers as needed
}
