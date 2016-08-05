package io.vertx.examples.spring;


import io.vertx.core.Vertx;
import io.vertx.examples.spring.context.ExampleSpringConfiguration;
import io.vertx.examples.spring.service.ProductService;
import io.vertx.examples.spring.verticle.ServerVerticle;
import io.vertx.examples.spring.verticle.SpringDemoVerticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Import({ExampleSpringConfiguration.class})
public class SpringExampleBootifiedApplication {

  @Autowired
  private ProductService productService;

  public static void main(String[] args) {

    // This is basically the same example as the web-examples static site example but it's booted using
    // SpringBoot, not Vert.x
    SpringApplication.run(SpringExampleBootifiedApplication.class, args);
  }

  @PostConstruct
  public void deployVerticle() {
      final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new SpringDemoVerticle(productService));
    vertx.deployVerticle(new ServerVerticle());
      System.out.println("Deployment done");
  }
}
