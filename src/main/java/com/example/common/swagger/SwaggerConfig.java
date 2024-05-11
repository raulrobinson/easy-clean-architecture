package com.example.common.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${springdoc.openapi.dev-url}")
    private String devUrl;

    private final SwaggerProperties swaggerProperties;

    @Autowired
    public SwaggerConfig(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean
    public OpenAPI myOpenAPI() {

        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription(this.swaggerProperties.getProjectShortDescription());

        Contact contact = new Contact();
        contact.setEmail(this.swaggerProperties.getDeveloperMail());
        contact.setName(this.swaggerProperties.getDeveloperName());
        contact.setUrl(this.swaggerProperties.getOrganizationUrl());

        License mitLicense = new License()
                .name("MIT License")
                .url(this.swaggerProperties.getProjectLicenceLink());

        Info info = new Info()
                .title(this.swaggerProperties.getProjectName())
                .version("1.0")
                .contact(contact)
                .description(this.swaggerProperties.getProjectShortDescription())
                .termsOfService(this.swaggerProperties.getProjectLicenceMsg())
                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
    }
}
