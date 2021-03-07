package com.example.inflearn.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        var ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        var bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();

        assertThat(bean1.getCount()).isEqualTo(1);


        var bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();

        assertThat(bean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUserPrototype() {
        var ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        var bean = ac.getBean(ClientBean.class);
        int count1 = bean.logic();

        assertThat(count1).isEqualTo(1);

        var bean2 = ac.getBean(ClientBean.class);
        int count2 = bean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {

        private Provider<PrototypeBean> prototypeBeanProvider;

        public ClientBean(Provider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }


    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count ++;
        }

        public int getCount() {
            return count;
        }
        
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy" + this);
        }
    }
}
