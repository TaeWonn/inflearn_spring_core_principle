package com.example.inflearn;

import com.example.inflearn.discount.DisCountPolicy;
import com.example.inflearn.discount.FixDiscountPolicy;
import com.example.inflearn.discount.RateDiscountPolicy;
import com.example.inflearn.member.MemberRepository;
import com.example.inflearn.member.MemberService;
import com.example.inflearn.member.MemberServiceImpl;
import com.example.inflearn.member.MemoryMemberRepository;
import com.example.inflearn.order.OrderService;
import com.example.inflearn.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(getMemberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(getMemberRepository(), disCountPolicy());
    }

    @Bean
    public DisCountPolicy disCountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


    public MemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }
}
