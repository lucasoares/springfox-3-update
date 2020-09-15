package br.com.test.app.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

@Configuration
public class SwaggerSetup {
  @Bean
  public Docket createDocketApi() {
    return new Docket(DocumentationType.OAS_30).select().paths(PathSelectors.any()).build();
  }

  @Bean
  public UiConfiguration uiConfig() {
    return UiConfigurationBuilder.builder()
        .docExpansion(DocExpansion.NONE)
        .defaultModelRendering(ModelRendering.EXAMPLE)
        .build();
  }
}
