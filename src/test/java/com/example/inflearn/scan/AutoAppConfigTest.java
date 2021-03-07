package com.example.inflearn.scan;

import com.example.inflearn.AutoAppConfig;
import com.example.inflearn.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        var ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        var bean = ac.getBean(MemberService.class);
        assertThat(bean).isInstanceOf(MemberService.class);
    }
}
