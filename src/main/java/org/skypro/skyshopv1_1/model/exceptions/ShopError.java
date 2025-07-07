package org.skypro.skyshopv1_1.model.exceptions;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ShopError {
    private final String code;

    private final String message;
}
