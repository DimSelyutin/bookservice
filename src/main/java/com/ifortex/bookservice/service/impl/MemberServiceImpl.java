package com.ifortex.bookservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ifortex.bookservice.model.Member;
import com.ifortex.bookservice.repository.MemberRepository;
import com.ifortex.bookservice.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    /*
     * {@inheritDoc}
     */
    @Override
    public Member findMember() {
        String genre = "Romance";
        return memberRepository.findFirstMemberByGenre(genre);
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public List<Member> findMembers() {
        return memberRepository.findAllMembers();
    }

}
