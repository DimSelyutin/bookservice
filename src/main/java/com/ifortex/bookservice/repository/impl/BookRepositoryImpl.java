package com.ifortex.bookservice.repository.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

import com.ifortex.bookservice.repository.BookRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
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
                        String jpql = "SELECT b.genre, COUNT(b) " +
                                        "FROM Book b " +
                                        "GROUP BY b.genre " +
                                        "ORDER BY COUNT(b) DESC";

                        Query query = entityManager.createNativeQuery(jpql);
                        List<Object[]> resultList = query.getResultList();

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
