package org.skypro.skyshopv1_1.model.service;

import lombok.RequiredArgsConstructor;
import org.skypro.skyshopv1_1.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {


    private final StorageService storageService;

    public List<SearchResult> search(String searchString) {

        return storageService.getSearchables().stream()
                .filter(s -> s.searchTerm().contains(searchString))
                .map(SearchResult::fromSearchable)
                .toList();
    }
}
