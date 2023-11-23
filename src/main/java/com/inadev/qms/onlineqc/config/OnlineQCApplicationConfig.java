package com.inadev.qms.onlineqc.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OnlineQCApplicationConfig implements WebMvcConfigurer {

	@Bean(name = "messageSource")
	public ResourceBundleMessageSource getMessageSource() {
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setDefaultEncoding("UTF-8");
		resourceBundleMessageSource.setBasenames("classpath:/messages/api_error_messages",
				"classpath:/messages/api_response_messages");
		resourceBundleMessageSource.setCacheSeconds(3600);
		return resourceBundleMessageSource;
	}


}
