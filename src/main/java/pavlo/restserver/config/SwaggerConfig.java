package pavlo.restserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private BasicAuthenticationPoint basicAuthenticationPoint;

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("pavlo.restserver.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }

    public ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot REST API",
                "Spring Boot REST API for GoIT final project",
                "1.0",
                "Terms of service",
                new Contact("Pavlo Dudkin", "www.goit.ua", "pavlovip@ukr.net"),
                "Apache License Version 2.0",
                "www.goit.ua");

        return apiInfo;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable();

        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET)
                .hasRole("USER");

        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST)
                .hasRole("MODERATOR");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET)
                .hasRole("MODERATOR");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE)
                .hasRole("MODERATOR");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT)
                .hasRole("MODERATOR");

        httpSecurity.authorizeRequests().anyRequest().hasRole("ADMIN");

        httpSecurity.httpBasic().authenticationEntryPoint(basicAuthenticationPoint);

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("12345")
                .roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("12345")
                .roles("USER", "ADMIN");
        auth.inMemoryAuthentication().withUser("moderator").password("12345")
                .roles("MODERATOR");
    }
}
