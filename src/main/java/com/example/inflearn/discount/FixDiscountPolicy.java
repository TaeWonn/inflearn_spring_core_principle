package com.example.inflearn.discount;

import com.example.inflearn.member.Grade;
import com.example.inflearn.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DisCountPolicy {

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP)
            return discountFixAmount;
        else
            return 0;
    }
}
