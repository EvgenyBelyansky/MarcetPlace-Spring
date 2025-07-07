package org.skypro.skyshopv1_1.model.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Getter
public abstract class ShopException extends RuntimeException {

    private ShopErrorCode shopErrorCode;
    private HttpStatus httpStatus;

    public ShopException(ShopErrorCode shopErrorCode, String message, HttpStatus httpStatus) {
        super(message);
        this.shopErrorCode = shopErrorCode;
        this.httpStatus = Optional.ofNullable(httpStatus).orElse(HttpStatus.I_AM_A_TEAPOT);
    }
}
