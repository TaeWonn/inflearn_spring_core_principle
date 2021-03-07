package com.example.inflearn.discount;

import com.example.inflearn.member.Member;

public interface DisCountPolicy {

    /**
     *
     * @param member
     * @param price
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);

}
