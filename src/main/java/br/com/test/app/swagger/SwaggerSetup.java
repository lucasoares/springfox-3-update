package br.com.test.app.swagger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

@Configuration
public class SwaggerSetup {
  private static final String PASSKEY = "passkey";
  private static final String SESSION_ID = "stilingue-session-id";

  @Value("${documentation.session-id.enabled:true}")
  private boolean enableSessionIdHeader;

  @Value("${passkey.enabled:true}")
  private boolean enablePassKey;

  @Bean
  public Docket createDocketApi() {
    Docket docket = createDocket();
    /*
    if (this.enablePassKey || this.enableSessionIdHeader) {
      docket.securitySchemes(securitySchemes()).securityContexts(securityContext());
    }*/

    return docket;
  }

  private Docket createDocket() {
    return new Docket(DocumentationType.OAS_30).select().paths(PathSelectors.any()).build();
    /*        .apiInfo(apiInfo())
    .directModelSubstitute(Date.class, Long.class)
    .useDefaultResponseMessages(true)*/
    /*.enable(this.enableDoc)
            .select()
    */
    /*        .apis(RequestHandlerSelectors.basePackage("br.com.stilingue"))
    .apis(CustomRequestPredicate.withoutClassAnnotation(FeignClient.class))*/
    /*
    .paths(PathSelectors.any())
    .build();*/
  }

  private List<SecurityScheme> securitySchemes() {
    List<SecurityScheme> schemes = new ArrayList<>();

    if (this.enablePassKey) {
      schemes.add(new ApiKey(PASSKEY, PASSKEY, "query"));
    }

    if (this.enableSessionIdHeader) {
      schemes.add(new ApiKey(SESSION_ID, SESSION_ID, "header"));
    }

    return schemes;
  }

  private List<SecurityContext> securityContext() {
    return Collections.singletonList(
        SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.any())
            .build());
  }

  private List<SecurityReference> defaultAuth() {
    List<SecurityReference> securityReferences = new ArrayList<>();

    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[] {authorizationScope};

    if (this.enablePassKey) {
      securityReferences.add(new SecurityReference(PASSKEY, authorizationScopes));
    }

    if (this.enableSessionIdHeader) {
      securityReferences.add(new SecurityReference(SESSION_ID, authorizationScopes));
    }

    return securityReferences;
  }

  private ApiInfo apiInfo() {
    Contact contact =
        new Contact(
            "Backend Stilingue", "https://www.stilingue.com.br", "backend@stilingue.com.br");

    return new ApiInfoBuilder()
        .title("Test App")
        .description("Description")
        .contact(contact)
        .version("1.0.0-SNAPSHOT")
        .build();
  }

  @Bean
  public UiConfiguration uiConfig() {
    return UiConfigurationBuilder.builder()
        .docExpansion(DocExpansion.NONE)
        .defaultModelRendering(ModelRendering.EXAMPLE)
        .build();
  }
}
