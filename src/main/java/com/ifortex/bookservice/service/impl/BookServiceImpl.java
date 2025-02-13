package com.ifortex.bookservice.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifortex.bookservice.exception.FailedFetchBookException;
import com.ifortex.bookservice.repository.BookRepository;
import com.ifortex.bookservice.service.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public Map<String, Long> getBooks() {
        try {
            log.debug("Fetching book count by genre");

            Map<String, Long> result = bookRepository.countBooksByGenre();

            log.debug("Successfully fetched {} genres", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching book count by genre: {}", e.getMessage(), e);
            throw new FailedFetchBookException("Failed to fetch book count by genre", e);
        }
    }

}
