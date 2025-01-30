package com.ifortex.bookservice.repository.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.ifortex.bookservice.model.Member;
import com.ifortex.bookservice.repository.MemberRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
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
            String jpql = "SELECT m FROM Member m JOIN m.books b WHERE b.genre = :genre ORDER BY b.publicationDate ASC";
            TypedQuery<Member> query = entityManager.createQuery(jpql, Member.class);
            query.setParameter("genre", genre);
            query.setMaxResults(1);

            List<Member> members = query.getResultList();
            Member firstMember = members.isEmpty() ? null : members.get(0);

            log.debug("findFirstMemberByGenre end - found: {}", firstMember);
            return firstMember;

        } catch (PersistenceException e) {
            log.error("findFirstMemberByGenre PersistenceException: {}", e.getMessage());
            throw new RuntimeException("Ошибка при выполнении запроса к базе данных", e);
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
            String jpql = "SELECT m FROM Member m " +
                    "LEFT JOIN m.memberBooks mb " +
                    "WHERE mb IS NULL";

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
