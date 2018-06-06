package guru.springframework.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import guru.springframework.utils.ConfigurationUtils;

/*
 * The purpose of the notes below is to have some reminders of how NOT to create tests under a Spring MVC controller
 */
/*
 * Enable the embedded database so that our repositories should now work
 */
//@DataJpaTest
//@WebMvcTest(IndexController.class)
/*By setting these annotations they will collide each other*/
//@ContextConfiguration(classes=WebConfig.class)
//@ComponentScan(basePackages= {"guru.springframework.service",
//"guru.springframework.repository"})
/*
* If we do not provide a class for WebApplicationContext you will run into this
* error stack.
* 
* Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException:
* No qualifying bean of type
* 'org.springframework.web.context.WebApplicationContext' available: expected
* at least 1 bean which qualifies as autowire candidate. Dependency
* annotations:
* {@org.springframework.beans.factory.annotation.Autowired(required=true)} at
* org.springframework.beans.factory.support.DefaultListableBeanFactory.
* raiseNoMatchingBeanFound(DefaultListableBeanFactory.java:1509)
* ~[spring-beans-5.0.4.RELEASE.jar:5.0.4.RELEASE]
* 
*/
/*
* Providing an implementation for WebApplicationContext
*/
//@ContextConfiguration(classes = WebConfig.class)

/*
* We need to provide another annotation as with these last are not enough:
* 
* java.lang.IllegalStateException: Unable to retrieve @EnableAutoConfiguration
* base packages
*/
//@WebAppConfiguration

/*
* This solves it but ... org.springframework.beans.BeanInstantiationException:
* Failed to instantiate [org.springframework.web.servlet.HandlerMapping]:
* Factory method 'resourceHandlerMapping' threw exception; nested exception is
* java.lang.IllegalStateException: No ServletContext set
* 
* ...
* 
* java.lang.IllegalStateException: No ServletContext set
*/
//@EnableAutoConfiguration
/*
* As a reference:
* https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference
* /htmlsingle/#spring-mvc-test-framework
* http://www.baeldung.com/spring-webappconfiguration
*/

//Now, the good ones :-) thanks to this resource : https://spring.io/guides/gs/testing-web/
/*
 * To make the spring context brings up we annotate the class. 
 * This enables the spring context.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
/*
 * This annotation sets the MockMVC properly, so that we no longer need to write something 
 * like the following when we load previous configuration on @Before jUnit test cycle:
 * this.mockMVC = MockMvcBuilders.webAppContextSetup(WebApplicationContext).build();
 */
@AutoConfigureMockMvc

public class RecipeControllerTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private WebApplicationContext wac;

	// @Autowired
	// RecipeRepository recipeRepository;
	//
	// @Autowired
	// UnitOfMeasureRepository unitOfMeasureRepository;

	@Before
	public void setUp() throws Exception {
		//final String indexController = "indexController";
		ConfigurationUtils.showLoadedBeans(wac);
		//ConfigurationUtils.isBeanLoaded(wac, indexController);
		/*
		 * As we need to enable Spring context we no longer use standalone setup.
		 */
		// this.mockMVC = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testMockMVCWithPathParam() throws Exception {
		
		final Long id = 3L;
		MvcResult mvcResult = mockMVC.perform(get("/recipes/{id}", id))//.andReturn();
		 .andExpect(status().is2xxSuccessful())
		 .andExpect(view().name("recipeDetail"))
		 .andExpect(model().attributeExists("recipe"))
		 //This will give a detailed info about the request and response
		 .andDo(print())
		 .andReturn();
		
		System.out.println("====================*************==================");
		int status = mvcResult.getResponse().getStatus();
		System.out.println(">>Status : " + mvcResult.getResponse().getStatus());
		System.out.println("=============================================================================");
		if(status != 200) {
			System.out.println(">>Content response : " + mvcResult.getResponse().getContentAsString());
		}
		
	}

}
