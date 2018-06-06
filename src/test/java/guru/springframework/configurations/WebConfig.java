package guru.springframework.configurations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import guru.springframework.model.Recipe;
import guru.springframework.services.RecipeService;

/*
 * https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurerAdapter.html
 * WebMvcConfigurerAdapter is deprecated as of Spring 5. That is the reason why we are encouraged to utilize WebMvcConfigurer interface
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	ApplicationContext ctx; 
	
	public WebConfig(ApplicationContext ctx) {
		this.ctx = ctx;
	}

	public ApplicationContext getCtx() {
		return ctx;
	}

	public void setCtx(ApplicationContext ctx) {
		this.ctx = ctx;
	}
	
	
	/*
	 * In order to fix:
	 * 
	 *  Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: 
	 *  No qualifying bean of type 'guru.springframework.services.RecipeService' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations:
	 *  
	 * It leads to this error
	 * 
	 * Caused by: org.springframework.beans.factory.BeanCurrentlyInCreationException: Error creating bean with name 'recipeService': Requested bean is currently in creation: Is there an unresolvable circular reference?
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.beforeSingletonCreation(DefaultSingletonBeanRegistry.java:345) ~[spring-beans-5.0.4.RELEASE.jar:5.0.4.RELEASE]
	
	 */
//	@Bean
//	public RecipeService recipeService() {
//		RecipeService rs = (RecipeService) ctx.getBean(RecipeService.class);
//		return rs;
//	}
}
