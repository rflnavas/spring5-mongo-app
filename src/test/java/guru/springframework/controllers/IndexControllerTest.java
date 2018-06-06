package guru.springframework.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import guru.springframework.model.Recipe;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.services.CategoryServiceImpl;
import guru.springframework.services.RecipeService;

/**
 * Here is a jUnit class to test a Spring MVC controller. We must note that repositories are mocks so that they will not work as a REAL repository.
 * For example, if you are trying to save a Recipe entity it will not be stored in DB.
 */
public class IndexControllerTest {

	@Mock
	RecipeService recipeServiceMock;
	
	CategoryServiceImpl categoryService;
	
	@Mock
	CategoryRepository categoryRepository;
	
	@Mock
	Model model;
	
	IndexController indexController;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
//		recipeService = new RecipeServiceImpl(recipeRepository);
		indexController = new IndexController(recipeServiceMock);
		categoryService = new CategoryServiceImpl(categoryRepository);
	}

	@Test
	public void testIndexController() {
		
		//given
		Set<Recipe>  recipes = new LinkedHashSet<>();
		Recipe r1 = new Recipe();
		r1.setId(1L);
		Recipe r2 = new Recipe();
		r2.setId(2L);
		Recipe r3 = new Recipe();
		r3.setId(3L);
		recipes.add(r1);
		recipes.add(r2);
		recipes.add(r3);
		
		//When the getRecipes from recipeService is invoked
		when(recipeServiceMock.getRecipes()).thenReturn(recipes);
		
		@SuppressWarnings("unchecked")
		ArgumentCaptor<Set<Recipe>> argCaptor = ArgumentCaptor.forClass(Set.class);
		//when
		
		/*
		 * 1- involves invoking recipeService once.
		 * 2- The recipes Set attribute will only have an empty Recipe.
		 */
		String viewName = indexController.getIndexPage(model); 
		
		//then
		assertEquals("index", viewName);
		verify(recipeServiceMock, times(1)).getRecipes();
		/*
		 * org.mockito.exceptions.misusing.InvalidUseOfMatchersException: Invalid use of
		 * argument matchers! 2 matchers expected, 1 recorded: -> at
		 * guru.springframework.controllers.IndexControllerTest.testIndexController(
		 * IndexControllerTest.java:42)
		 * 
		 * This exception may occur if matchers are combined with raw values:
		 * //incorrect: someMethod(anyObject(), "raw String");
		 */
		//verify(model, times(1)).addAttribute("recipes", anySet());
		//All parameters must be Matchers
		//Test whether it has a Set in recipes. This is not enough if we want to dig into the set
		verify(model, times(1)).addAttribute(eq("recipes"), anySet());
		
		verify(model, times(1)).addAttribute(eq("recipes"), argCaptor.capture());
		Set<Recipe> setInController = argCaptor.getValue();
		setInController.forEach(r -> System.out.println(r));
		assertEquals(3, setInController.size());
	}
	
	
	@Test
	public void testMockMVC() throws Exception {
		/*
		 * This is the way we make use of the minimum configuration to test a group of
		 * controllers without the need of loading full Spring context
		 */
		MockMvc mockMVC = MockMvcBuilders.standaloneSetup(indexController).build();
		
		MvcResult mvcResult =  mockMVC.perform(get("/"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("index")).andReturn();
		
		System.out.println(mvcResult.getResponse().getStatus());
	}
	
	@Test
	@Ignore
	/*
	 * This won't work. I cannot find a way to make it work
	 */
	public void testIndexController2() {
		final Long id = 1L;
		Recipe rec1 = new Recipe();
		rec1.setId(id);
		rec1.setDescription("Mock recipe");
		recipeServiceMock.create(rec1);
		when(recipeServiceMock.findById(id)).thenReturn(Optional.of(rec1));

		ArgumentCaptor<Recipe> argCaptorRecipe = ArgumentCaptor.forClass(Recipe.class);
		
		Optional<Recipe> optRecipe = recipeServiceMock.findById(id);
		/**
		 *  If we forget using verify, we will this stack trace error giving additional suggestions
		 *  org.mockito.exceptions.base.MockitoException: 
			No argument value was captured!
			You might have forgotten to use argument.capture() in verify()...
			...or you used capture() in stubbing but stubbed method was not called.
			Be aware that it is recommended to use capture() only with verify()
	
			Examples of correct argument capturing:
		    ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
		    verify(mock).doSomething(argument.capture());
		    assertEquals("John", argument.getValue().getName());
		 */
		verify(recipeServiceMock, times(1)).findById(id);
		
		Recipe recipe = argCaptorRecipe.getValue();
		System.out.println("testIndexController2 : " + recipe);
	}

}
