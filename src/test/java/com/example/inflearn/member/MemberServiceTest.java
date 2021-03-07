package com.example.inflearn.member;

import static org.assertj.core.api.Assertions.*;

import com.example.inflearn.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    void beforeEach() {
        memberService = new AppConfig().memberService();
    }

    @Test
    void join() {
        //given
        var member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        var findMember = memberService.findMember(1L);

        //then
        assertThat(member).isEqualTo(findMember);
    }
}