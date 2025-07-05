package org.skypro.skyshopv1_1.model.exceptions;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShopError {
    private final String code;

    private final String message;
}
