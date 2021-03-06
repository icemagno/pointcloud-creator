package br.mil.defesa.sisgeodef;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.basePackage("br.mil.defesa.sisgeodef.controller") )
				.paths( PathSelectors.any() )
				.build()
				.apiInfo( this.informacoesApi().build() );

	}


	private ApiInfoBuilder informacoesApi() {
 
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
 
		apiInfoBuilder.title("POINTGEN");
		apiInfoBuilder.description("Gerador de Nuvem de Pontos");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.termsOfServiceUrl("#");
		apiInfoBuilder.license("Licença - Open Source");
		apiInfoBuilder.licenseUrl("http://www.casnav.mb");
		apiInfoBuilder.contact(this.contato());
 
		return apiInfoBuilder;
 
	}
	
	
	private Contact contato() {
 		return new Contact(	"Centro de Análises de Sistemas Navais - CASNAV", "http://www.casnav.mb", "");	
	}
	
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/fonts/**").addResourceLocations("file:///./fonts/")
                .setCachePeriod(0);
    }

}
