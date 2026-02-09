package com.example.boardv1._core.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    // Spring Boot에 어느 필터를 적용할지 알려주는 등록용 객체
    //

    // FirstFilter를 언제, 어디에, 어떤 순서로 실행할지 정하기
    @Bean
    public FilterRegistrationBean<FirstFilter> firstFilter() {
        FilterRegistrationBean<FirstFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new FirstFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(1);
        return bean;
    }

    // LoginFilter를 언제, 어디에, 어떤 순서로 실행할지 정하기
    // @Bean
    // public FilterRegistrationBean<LoginFilter> loginFilter() {
    // FilterRegistrationBean<LoginFilter> bean = new FilterRegistrationBean<>();
    // bean.setFilter(new LoginFilter());
    // bean.addUrlPatterns(
    // "/boards/*",
    // "/replies/*");
    // bean.setOrder(2);
    // return bean;
    // }
}
