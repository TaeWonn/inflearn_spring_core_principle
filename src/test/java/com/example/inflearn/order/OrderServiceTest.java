package com.example.inflearn.order;

import com.example.inflearn.AppConfig;
import com.example.inflearn.member.Grade;
import com.example.inflearn.member.Member;
import com.example.inflearn.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        //given
        Long memberId = 1L;
        var member = new Member(memberId, "memberA", Grade.VIP);

        //when
        memberService.join(member);

        var order = orderService.createOrder(memberId, "itemA", 10000);

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}