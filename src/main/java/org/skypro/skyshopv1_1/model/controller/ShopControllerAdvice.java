package org.skypro.skyshopv1_1.model.controller;

import org.skypro.skyshopv1_1.model.exceptions.ShopError;
import org.skypro.skyshopv1_1.model.exceptions.ShopException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(ShopException.class)
    public ResponseEntity<ShopError> handleShopException(ShopException e) {
        ShopError error = new ShopError(
                e.getShopErrorCode(),
                e.getMessage()
        );

        return ResponseEntity
                .status(e.getHttpStatus())
                .body(error);
    }
}
