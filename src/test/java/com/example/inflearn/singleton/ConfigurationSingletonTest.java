package com.example.inflearn.singleton;

import com.example.inflearn.AppConfig;
import com.example.inflearn.member.MemberServiceImpl;
import com.example.inflearn.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {


    @Test
    void configurationTest() {
        var ac = new AnnotationConfigApplicationContext(AppConfig.class);

        var memberService = ac.getBean("memberService", MemberServiceImpl.class);
        var orderService = ac.getBean("orderService", OrderServiceImpl.class);
        //var memberRep = ac.getBean("getMemberRepository", MemberRepository.class);

        var memberRepository1 = memberService.getMemberRepository();
        var memberRepository2 = orderService.getMemberRepository();

        //System.out.println("memberRepository = " + memberRep);
        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);

        //assertThat(memberRepository1).isSameAs(memberRepository2);
    }

    @Test
    void configurationDeep() {
        var ac = new AnnotationConfigApplicationContext(AppConfig.class);
        var bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }

}
