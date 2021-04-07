package com.mobius.moa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.Contact;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private String version;
    private String title;

    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfo(
                title,
                "MOA API Docs",
                version,
                "www.example.com",
                new Contact("Contact Me", "www.example.com", "foo@example.com"),
                "Licenses",
                "www.example.com",
                new ArrayList<>());
    }

    @Bean
    public Docket api() {
        version = "V1";
        title = "MOA API";
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(apiInfo(title, version));

    }
}
