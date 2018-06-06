package guru.springframework.repositories;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.model.Category;
import guru.springframework.model.Difficulty;
import guru.springframework.model.Ingredient;
import guru.springframework.model.Notes;
import guru.springframework.model.Recipe;
import guru.springframework.model.UnitOfMeasure;
import guru.springframework.model.Ingredient.IngredientBuilder;
import guru.springframework.model.Recipe.RecipeBuilder;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
//https://www.leveluplunch.com/java/tutorials/022-preload-database-execute-sql-spring-testing/
//@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
/*
 * Enables spring data repositories
 */
@DataJpaTest
public class RecipeRepositoryTest {

//	@Autowired
//	private TestEntityManager entityManager;
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testSaveRecipe() {
		UnitOfMeasure tableSpoon = unitOfMeasureRepository.findByMeasure("Table spoon").get();
		UnitOfMeasure teaSpoon = unitOfMeasureRepository.findByMeasure("Tea spoon").get();
		Ingredient ing1 = IngredientBuilder.of().amount(2).description("ancho chili powder").unitOfMeasure(tableSpoon)
				.create();
		Ingredient ing2 = IngredientBuilder.of().amount(1).description("dried oreganor").unitOfMeasure(teaSpoon)
				.create();
		// @SuppressWarnings("serial")
		Recipe guacRecipe = RecipeBuilder.of().description("Perfect Guacamole")
				// .ingredients(new HashSet<Ingredient>() {
				// {
				// add(ing1);
				// add(ing2);
				// }
				// })
				.withIngredients(ing1, ing2)
				.directions(
						"1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon"
								+ "\n"
								+ "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)"
								+ "\n"
								+ "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n"
								+ "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n"
								+ "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n"
								+ "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n"
								+ "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n"
								+ "\n" + "\n"
								+ "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd")
				.notes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n"
						+ "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n"
						+ "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n"
						+ "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n"
						+ "\n" + "\n"
						+ "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws")
				.difficulty(Difficulty.MODERATE).prepTime(60).servings(4).create();

		recipeRepository.save(guacRecipe);
		
		assertThat(recipeRepository.count(), greaterThan(0L));
		Recipe recipeLoaded = recipeRepository.findAll().iterator().next();
		assertEquals("Perfect Guacamole", recipeLoaded.getDescription());
	}

	@Test
	public void testCountUnitMeasures() {
		long countRecipes = unitOfMeasureRepository.count();
		assertThat(countRecipes, greaterThan(0L));
	}
	
	@Test
	public void testSaveUnitMeasure() {
		UnitOfMeasure tableSpoon = new UnitOfMeasure("A ton");
		unitOfMeasureRepository.save(tableSpoon);
		
		assertThat(unitOfMeasureRepository.count(), greaterThan(0L));
		
	}

}
