package com.ifortex.bookservice.repository;

import java.util.Map;

import com.ifortex.bookservice.model.Book;

public interface BookRepository {

    /**
     * Returns the count of the {@link Book}s by each genre, ordered from the genre
     * with the most
     * books to the least
     *
     * @return desired result as a Map where key is book genre and value is count of
     *         the books
     */
    Map<String, Long> countBooksByGenre();

}
