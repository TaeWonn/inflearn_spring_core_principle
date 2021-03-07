package com.example.inflearn.autowired;

import com.example.inflearn.AutoAppConfig;
import com.example.inflearn.discount.DisCountPolicy;
import com.example.inflearn.member.Grade;
import com.example.inflearn.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    void findAllBean() {
        var ac = new AnnotationConfigApplicationContext(AutoAppConfig.class ,DiscountService.class);

        var bean = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "userA", Grade.VIP);

        int discountPrice = bean.discount(member, 10000, "fixDiscountPolicy");

        assertThat(bean).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = bean.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DisCountPolicy> policyMap;
        private final List<DisCountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DisCountPolicy> policyMap, List<DisCountPolicy> policies) {
            System.out.println("policyMap = " + policyMap + ", policies = " + policies);
            this.policyMap = policyMap;
            this.policies = policies;
        }

        public int discount(Member member, int price, String discountCode) {
            DisCountPolicy disCountPolicy = policyMap.get(discountCode);
            return disCountPolicy.discount(member, price);
        }
    }
}
