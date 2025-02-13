package com.ifortex.bookservice.repository.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

import com.ifortex.bookservice.repository.BookRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BookRepositoryImpl implements BookRepository {

        @PersistenceContext
        private EntityManager entityManager;

        /*
         * {@inheritDoc}
         */
        @Override
        public Map<String, Long> countBooksByGenre() {
                log.debug("countBooksByGenre start");

                try {
                        String jpql = "SELECT g.name, COUNT(b) " + // Предполагается, что у вас есть сущность Genre с
                                                                   // полем name
                                        "FROM Book b JOIN b.genres g " + // Используем JOIN для получения жанров
                                        "GROUP BY g.name " +
                                        "ORDER BY COUNT(b) DESC";

                        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
                        List<Object[]> resultList = query.getResultList();

                        // Проверяем, если список результатов пуст
                        if (resultList.isEmpty()) {
                                log.warn("No genres found");
                                return Collections.emptyMap();
                        }

                        Map<String, Long> genreCountMap = resultList.stream()
                                        .collect(Collectors.toMap(
                                                        arr -> (String) arr[0],
                                                        arr -> ((Number) arr[1]).longValue()));

                        log.debug("countBooksByGenre end - found {} genres", genreCountMap.size());
                        return genreCountMap;

                } catch (PersistenceException e) {
                        log.error("countBooksByGenre PersistenceException: {}", e.getMessage());
                        throw new RuntimeException("Ошибка при выполнении запроса к базе данных", e);
                }
        }

}
