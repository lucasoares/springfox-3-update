package br.com.test.app;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class TestHealthApplication {
  public static void main(String[] args) {
    new SpringApplicationBuilder(TestHealthApplication.class).bannerMode(Mode.OFF).run(args);
  }
}
