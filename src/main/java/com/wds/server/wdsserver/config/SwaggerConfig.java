package com.wds.server.wdsserver.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo("Wds-server",
                        "wds-server Restful API Documentation",
                        "1.0",
                        "",
                        new Contact("wds-server", "www.naver.com", "dlwoen9@naver.com"),
                        "Copyright 2019 wds-server. All rights Reserved.",
                        "http://www.naver.com",
                        new ArrayList<>()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wds.server.wdsserver"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, getResponseMessage())
                .globalResponseMessage(RequestMethod.POST, getResponseMessage())
                .globalResponseMessage(RequestMethod.PUT, getResponseMessage())
                .globalResponseMessage(RequestMethod.DELETE, getResponseMessage());
    }

    private List<ResponseMessage> getResponseMessage() {
        return Lists.newArrayList(new ResponseMessageBuilder().code(200).message("성공").build(),
                new ResponseMessageBuilder().code(201).message("생성 완료").build(),
                new ResponseMessageBuilder().code(204).message("성공-본문없음").build(),
                new ResponseMessageBuilder().code(400).message("유효성 검증 오류").build(),
                new ResponseMessageBuilder().code(401).message("인증 필요").build(),
                new ResponseMessageBuilder().code(404).message("존재하지 않는 API ").build(),
                new ResponseMessageBuilder().code(500).message("서버 오류").build());
    }

}
