package com.ifortex.bookservice.repository.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ifortex.bookservice.model.Member;
import com.ifortex.bookservice.repository.MemberRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Class repository for work with members.
 * author DimSelyutin.
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final EntityManager entityManager;

    /*
     * {@inheritDoc}
     */
    @Override
    public Member findFirstMemberByGenre(String genre) {
        log.debug("findFirstMemberByGenre start - genre: {}", genre);
        try {
            // Native SQL query that uses PostgreSQL array operator
            String sql = "SELECT m.* FROM members m " +
                    "JOIN member_books mb ON m.id = mb.member_id " +
                    "JOIN books b ON mb.book_id = b.id " +
                    "WHERE b.genre @> ?::varchar[] ORDER BY b.publication_date ASC LIMIT 1";

            // Create a native query
            Query query = entityManager.createNativeQuery(sql, Member.class);
            // Set parameter as an array by wrapping genre in an array
            query.setParameter(1, new String[] { genre });

            // Get the single result or handle no result scenario
            Member firstMember = (Member) query.getSingleResult();

            log.debug("findFirstMemberByGenre end - found: {}", firstMember);
            return firstMember;

        } catch (NoResultException e) {
            log.debug("findFirstMemberByGenre end - no member found for genre: {}", genre);
            return null; // Return null if no member is found
        } catch (PersistenceException e) {
            log.error("findFirstMemberByGenre PersistenceException: {}", e.getMessage());
            throw new RuntimeException("Error executing database query", e);
        }
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public List<Member> findAllMembers() {
        log.debug("findAllMembers start");

        List<Member> members;
        try {
            String jpql = "SELECT m FROM Member m LEFT JOIN m.borrowedBooks mb WHERE mb IS NULL";

            members = entityManager.createQuery(jpql, Member.class).getResultList();

            log.debug("findAllMembers end - found {} members", members.size());
        } catch (NoResultException e) {
            log.debug("findAllMembers NoResultException: no members found");
            return Collections.emptyList();
        } catch (PersistenceException e) {
            log.error("findAllMembers PersistenceException: {}", e.getMessage());
            throw new RuntimeException("Ошибка при выполнении запроса к базе данных", e);
        }
        return members;
    }

}
