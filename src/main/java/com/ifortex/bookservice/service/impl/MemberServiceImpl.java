package com.ifortex.bookservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifortex.bookservice.dto.BookGenreDto;
import com.ifortex.bookservice.exception.InvalidGenreException;
import com.ifortex.bookservice.model.Member;
import com.ifortex.bookservice.repository.MemberRepository;
import com.ifortex.bookservice.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    /*
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public Member findMember() {
        BookGenreDto bookGenreDto = new BookGenreDto("Romance");
        if (bookGenreDto == null || bookGenreDto.genre() == null) {
            throw new InvalidGenreException("Genre not should be empty!");
        }
        Member member = memberRepository.findFirstMemberByGenre(bookGenreDto.genre());
        log.info(member.toString());

        return member;
    }

    /*
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public List<Member> findMembers() {
        return memberRepository.findAllMembers();
    }

}
