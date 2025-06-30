package org.skypro.skyshopv1_1.model.search;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class SearchResult {

    private final UUID id;

    private final String name;

    private final String contentType;

    public static SearchResult fromSearchable(Searchable searchable) {
        return new SearchResult(searchable.getId(), searchable.getName(), searchable.getTypeContent());
    }


}
