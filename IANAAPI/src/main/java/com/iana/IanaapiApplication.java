package com.iana;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan()
public class IanaapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IanaapiApplication.class, args);
	}
	
	@Bean
    public Docket api() {
		List<ResponseMessage> list = new ArrayList<>();  
		list.add(new ResponseMessageBuilder()   
              .code(500)
              .message("500 message")
              .responseModel(new ModelRef("Error"))
              .build());
		
		list.add(new ResponseMessageBuilder() 
                .code(403)
                .message("Forbidden!")
                .build());
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()        
          /*.apis(RequestHandlerSelectors.any())              */
          .apis(RequestHandlerSelectors.basePackage("com.iana"))
          .paths(PathSelectors.any())
          .build()
          .apiInfo(apiInfo())
          .useDefaultResponseMessages(true)    //TRUE means gives first priority to default messages                                 
          .globalResponseMessage(RequestMethod.GET, list); //this will override or add new response messages. 
    }
	
	private ApiInfo apiInfo() {
	    ApiInfo apiInfo = new ApiInfo(
	      "IANA REST API",
	      "Some custom description of API.",
	      "API TOS",
	      "Terms of service",
	      "chauhanvipul87@gmail.com",
	      "License of API",
	      "API license URL");
	    return apiInfo;
	}
	
	
}
