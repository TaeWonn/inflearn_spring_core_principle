package com.example.inflearn.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        var ac = new AnnotationConfigApplicationContext(TestConfig.class);
        var bean1 = ac.getBean(StatefulService.class);
        var bean2 = ac.getBean(StatefulService.class);

        //ThreadA: A사용자 10000원 주문
        int bean1price = bean1.order("userA", 10000);
        //ThreadB: B사용자 20000원 주문
        bean2.order("userB", 20000);

        //ThreadA: A사용자 주문 금액 조회
        //int price = bean1.getPrice();
        System.out.println("price = " + bean1price);

        assertThat(bean1price).isEqualTo(10000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
