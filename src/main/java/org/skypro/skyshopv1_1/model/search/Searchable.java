package org.skypro.skyshopv1_1.model.search;

import java.util.UUID;

public interface Searchable {

    String searchTerm();

    String getTypeContent();

    String getName();

    UUID getId();


    default String getStringRepresentation() {
        return String.format("Имя %s - тип %s", searchTerm(), getTypeContent());
    }


}
