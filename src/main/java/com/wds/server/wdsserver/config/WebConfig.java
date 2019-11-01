package com.wds.server.wdsserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public Interceptor interceptor() {
        return new Interceptor();
    }

    /**
     * WebConfig가 추가되면서 Swagger 설정을 위해 추가해야함
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 인터셉터 제외 url 설정
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor())
                .excludePathPatterns("/api/v1/error/**") /** 추후 삭제 */
                .excludePathPatterns("/resources/**")
                .excludePathPatterns("/favicon.ico")
                 // swagger
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/*/api-docs/**")
                ;
    }
}
