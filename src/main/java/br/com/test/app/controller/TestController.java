package br.com.test.app.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class TestController {

  @GetMapping("/world")
  public OkResponse helloWorld() throws InterruptedException {
    Thread.sleep(10);

    return new OkResponse();
  }

  @Data
  private static class OkResponse {
    private String status = "ok";
  }
}
