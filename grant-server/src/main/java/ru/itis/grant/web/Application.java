package ru.itis.grant.web;

import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.stereotype.Controller;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.Thread.sleep;

@Controller
@ComponentScan(basePackages = "ru.itis.grant")
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@EnableSwagger2
@EntityScan("ru.itis.grant.model")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        while (true) {
//            ping();
//            try {
//                sleep(5 * 60000);
//            } catch (InterruptedException e) {
//                System.out.println(e.getMessage());
//            }
//        }
    }
//
//    private static void ping() {
//        String url = "https://grant-sys.herokuapp.com/swagger-ui.html";
//        try {
//            URL obj = new URL(url);
//            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//            con.setRequestMethod("GET");
//            int responseCode = con.getResponseCode();
//            System.out.println("ping with code = " + responseCode);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.itis.grant.web.controllers"))
                .paths(PathSelectors.any())
                .build();
    }
}
