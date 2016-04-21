package com.test.doc;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfig  {
	/**
     * Every Docket bean is picked up by the swagger-mvc framework - allowing for multiple
     * swagger groups i.e. same code base multiple swagger resource listings.
     */
    @Bean
    public Docket customDocket(){
       Docket docket = new Docket(DocumentationType.SWAGGER_2)
               .select()
               .apis(RequestHandlerSelectors.any())
               .paths(this.pathsCustom())
               .build();
       docket.apiInfo(getApiInfo());
       docket.groupName("customer");
       docket.pathMapping("/v1");
       return docket;
    }
   
    @Bean
    public Docket merchantDocket(){
       Docket docket = new Docket(DocumentationType.SWAGGER_2)
               .select()
               .apis(RequestHandlerSelectors.any())
               .paths(this.pathsMerchant())
               .build();
       docket.apiInfo(getApiInfo());
       docket.groupName("merchant");
       docket.pathMapping("/v1");
       return docket;
    }
    @Bean
    public UiConfiguration uiConfig() {
    	return UiConfiguration.DEFAULT;
    }
    private ApiInfo getApiInfo(){
    	ApiInfoBuilder builder = new ApiInfoBuilder();
    	return builder.title("标题").description("描述").version("v2").license("")
    			.licenseUrl("").build();
    }
    
    @SuppressWarnings("unchecked")
    private Predicate<String> pathsCustom() {
      return or(regex("/hello.*"));
    }
    @SuppressWarnings("unchecked")
    private Predicate<String> pathsMerchant() {
      return or(regex("/foo.*"));
    }
    
    private Set<String> consumeJson(){
    	Set<String> consume = new HashSet<String>();
    	consume.add("application/json");
    	return consume;
    }
   
}
