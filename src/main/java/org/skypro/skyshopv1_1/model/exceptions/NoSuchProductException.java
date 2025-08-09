package org.skypro.skyshopv1_1.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoSuchProductException extends ShopException {
    public NoSuchProductException(UUID id) {
        super(ShopErrorCode.PRODUCT_NOT_FOUND, ("Продукт с id: [%s] найден!".formatted(id)), HttpStatus.NOT_FOUND);
    }
}
