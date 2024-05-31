// Step1

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication()
           .withUser("test")
           .password("{noop}123456") // {noop} is used to denote no encryption
           .roles("USER");
   }
 
   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
           .authorizeRequests()
           .anyRequest().authenticated()
           .and()
           .httpBasic(); // Use HTTP Basic authentication
   }
}

// Step2 /hello

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class HelloWorldController {
 
   @GetMapping("/hello")
   public String hello() {
       return "Hello, World!";
   }
}

// Step3 main

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
@SpringBootApplication
public class HelloWorldApplication {
 
   public static void main(String[] args) {
       SpringApplication.run(HelloWorldApplication.class, args);
   }
}

// Run

curl -u test:123456 http://localhost:8080/hello
