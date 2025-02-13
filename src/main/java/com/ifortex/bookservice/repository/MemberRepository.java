package com.ifortex.bookservice.repository;

import java.util.List;

import com.ifortex.bookservice.model.Member;

public interface MemberRepository {
    /**
     * Finds the {@link Member} who read the oldest book in Romance genre and who
     * was most recently
     * registered on the platform
     *
     * @return desired member
     */
    public Member findFirstMemberByGenre(String genre);

    /**
     * Finds {@link Member}s who register in 2023 year, but didn't read any books
     *
     * @return desired member
     */
    public List<Member> findAllMembers();
}
