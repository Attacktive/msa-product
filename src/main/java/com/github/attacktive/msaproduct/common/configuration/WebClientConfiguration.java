package com.github.attacktive.msaproduct.common.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfiguration {
	private final AppProperties appProperties;

	@Bean
	public WebClient webClient() {
		return WebClient.builder()
			.baseUrl(appProperties.getBaseUrlToOrders())
			.defaultHeaders(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
			.codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(-1))
			.build();
	}
}
