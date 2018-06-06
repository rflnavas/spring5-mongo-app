package guru.springframework.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;

/*
 * Remember that we run a test the bootstrap class won't actually be run so do not expect 
 * to have Recipe's table populated with a single row.
 */
public class RecipeServiceImplTest {

	RecipeServiceImpl recipeService;
	
	/*
	 * Since we need to inject the RecipeRepository we mark with the Mock annotation 
	 */
	@Mock
	RecipeRepository recipeRepository;
	
	@Before
	public void setUp() throws Exception {
		//Initializing mockito tests
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository);
	}

	@Test
	public void getRecipes() {
		
		Recipe recipe = new Recipe();
		Set<Recipe> recipesData = new LinkedHashSet<>();
		recipesData.add(recipe);
		
		when(recipeService.getRecipes()).thenReturn(recipesData);
		
		recipeRepository.findAll();
		
		Set<Recipe> recipes = recipeService.getRecipes(); //Will internally invoke findAll
		assertEquals(recipes.size(), 1);
		//Has the recipeRepository.findAll method been invoked only once?
		verify(recipeRepository, times(1)).findAll();
		
		//verify(recipeRepository, times(3)).findAll();
	}

}
