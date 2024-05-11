package com.example.common.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "springdoc.properties")
public class SwaggerProperties {
	private String projectName;
	private String projectShortDescription;
	private String developerName;
	private String developerMail;
	private String projectTosMsg;
	private String projectTosLink;
	private String projectLicenceMsg;
	private String projectLicenceLink;
	private String organizationUrl;
}
