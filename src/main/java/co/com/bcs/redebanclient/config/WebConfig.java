package co.com.bcs.redebanclient.config;

import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zalando.logbook.HttpLogFormatter;
import org.zalando.logbook.Sink;

import co.com.bcs.redebanclient.services.LoggingService;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor()
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public GroupedOpenApi depositoApi() {
		return GroupedOpenApi.builder().group("WS Redeban V1").packagesToScan("co.com.bcs.redebanclient.controller.v1")
				.build();
	}

	@Bean
	public OpenAPI metaData() {
		return new OpenAPI().info(new Info().title("WS Redeban V1")
				.description("WS Redeban Compra TC").version("1.0.0"));
	}

	@Bean
	public Sink sink(LoggingService loggingService, HttpLogFormatter httpLogFormatter) {
		return new SinkDatabase(loggingService, httpLogFormatter);
	}

}