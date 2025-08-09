package org.skypro.skyshopv1_1.model.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshopv1_1.TestHelper;
import org.skypro.skyshopv1_1.model.search.SearchResult;
import org.skypro.skyshopv1_1.model.search.Searchable;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    @DisplayName("Продукты есть и найдены")
    void search() {
        String searchString = "Тест";

        List<Searchable> searchableList = TestHelper.createRandomSearchables(searchString);

        Mockito.when(storageService.getSearchables()).thenReturn(searchableList);

        List<SearchResult> actual = searchService.search(searchString);

        assertThat(searchableList)
                .map(SearchResult::fromSearchable)
                .containsExactlyInAnyOrderElementsOf(actual);

        Mockito.verify(storageService, Mockito.times(1)).getSearchables();
    }

    @Test
    @DisplayName("Продуктов нет, возвращается пустой лист")
    void search2() {
        String searchString = "Тест";

        List<Searchable> searchableList = List.of();

        Mockito.when(storageService.getSearchables()).thenReturn(searchableList);

        List<SearchResult> actual = searchService.search(searchString);

        assertThat(actual).isEmpty();

        Mockito.verify(storageService, Mockito.times(1)).getSearchables();

    }

    @Test
    @DisplayName("Возвращает пустой лист, если продукты есть но они не подходят к поиску")
    void search3() {
        String searchString = "Тест";

        List<Searchable> searchableList = TestHelper.createRandomSearchables("Ne test");

        Mockito.when(storageService.getSearchables()).thenReturn(searchableList);

        List<SearchResult> actual = searchService.search(searchString);

        assertThat(actual).isEmpty();

        Mockito.verify(storageService, Mockito.times(1)).getSearchables();
    }

    @Test
    @DisplayName("Возвращает часть товаров, подходящих к поисковой строке")
    void search4() {
        String searchString = "Тест";

        List<Searchable> searchableList = TestHelper.createRandomSearchables(searchString);
        List<Searchable> searchableList2 = TestHelper.createRandomSearchables("Ne test");

        List<Searchable> searchableList3 = Stream.concat(searchableList.stream(), searchableList2.stream())
                .toList();

        Mockito.when(storageService.getSearchables()).thenReturn(searchableList3);

        List<SearchResult> actual = searchService.search(searchString);

        assertThat(searchableList)
                .map(SearchResult::fromSearchable)
                .containsExactlyInAnyOrderElementsOf(actual);

        Mockito.verify(storageService, Mockito.times(1)).getSearchables();
    }
}