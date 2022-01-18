package ie.dsch.be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import ie.dsch.be.preferences.UserPreferences;
import ie.dsch.be.security.Users;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Bharath
 *
 */
@SpringBootApplication
@EnableSwagger2
@EnableCaching
@EntityScan(basePackageClasses = {UserPreferences.class,Users.class})
public class Application {

  /**
 * @param args
 */
public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
@Bean
public Docket productApi() {
   return new Docket(DocumentationType.SWAGGER_2).select()
      .apis(RequestHandlerSelectors.any())              
              .paths(PathSelectors.any())                          
              .build();
}
}
