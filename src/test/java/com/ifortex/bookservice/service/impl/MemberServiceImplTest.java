package com.ifortex.bookservice.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ifortex.bookservice.config.TestContainersConfig;
import com.ifortex.bookservice.model.Member;
import com.ifortex.bookservice.repository.MemberRepository;

@SpringBootTest
public class MemberServiceImplTest extends TestContainersConfig {

    @Autowired
    private MemberServiceImpl memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testFindMember_Success() {
        Member member1 = new Member();
        member1.setName("Diana Prince");
        // Выполнение
        Member result = memberService.findMember();
        // Проверка
        assertNotNull(result);
        assertEquals(member1.getName(), result.getName());
    }

    @Test
    public void testFindMembers() {
        // Выполнение
        List<Member> result = memberService.findMembers();
        // Проверка
        assertNotNull(result);
        assertEquals(2, result.size());

    }
}
