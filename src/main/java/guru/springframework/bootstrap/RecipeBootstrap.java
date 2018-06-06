package guru.springframework.bootstrap;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.model.Category;
import guru.springframework.model.Difficulty;
import guru.springframework.model.Ingredient;
import guru.springframework.model.Ingredient.IngredientBuilder;
import guru.springframework.model.Notes;
import guru.springframework.model.Recipe;
import guru.springframework.model.Recipe.RecipeBuilder;
import guru.springframework.model.UnitOfMeasure;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.services.CategoryService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	// <ApplicationEvent> <-- Be cautious, as it will complain that there is some
	// kind of dependency cycle or anything weird. A silly mistake of mine

	@Autowired
	UnitOfMeasureService unitOfMeasureService;
	@Autowired
	RecipeService recipeService;

	@Autowired
	CategoryService categoryService;
	@Autowired
	IngredientRepository ingredientRepository;

	public RecipeBootstrap(UnitOfMeasureService unitOfMeasureService, RecipeService recipeService,
			IngredientRepository ingredientRepository) {
		this.unitOfMeasureService = unitOfMeasureService;
		this.recipeService = recipeService;
		this.ingredientRepository = ingredientRepository;
	}

	// private final UnitOfMeasureRepository unitOfMeasureRepository;
	// private final RecipeRepository recipeRepository;
	// private final IngredientRepository ingredientRepository;
	//
	// public RecipeBootstrap(UnitOfMeasureRepository unitOfMeasureRepository,
	// RecipeRepository recipeRepository,
	// IngredientRepository ingredientRepository) {
	// this.unitOfMeasureRepository = unitOfMeasureRepository;
	// this.recipeRepository = recipeRepository;
	// this.ingredientRepository = ingredientRepository;
	// }
	//
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) { // ApplicationEvent event
		createRecipes();
	}

	private void createRecipes() {
		// System.out.println(unitOfMeasureService.count() + " Recipes");
		// CollectionUtils.toStream(unitOfMeasureService.findAll(),
		// false).forEach((x)->System.out.println(x));
		UnitOfMeasure tableSpoon = unitOfMeasureService.getUnitOfMeasureByName("Table spoon").get(); // unitOfMeasure.getUnitOfMeasureByName("Table
																										// spoon").get();
		UnitOfMeasure teaSpoon = unitOfMeasureService.getUnitOfMeasureByName("Tea spoon").get();// unitOfMeasure.getUnitOfMeasureByName("Tea
																								// spoon").get();
		UnitOfMeasure cup = unitOfMeasureService.getUnitOfMeasureByName("Cup").get();
		UnitOfMeasure pint = unitOfMeasureService.getUnitOfMeasureByName("Pint").get();
		UnitOfMeasure each = unitOfMeasureService.getUnitOfMeasureByName("Each").get();
		Category american = categoryService.getCategoryByName("American").get();
		Category mexican = categoryService.getCategoryByName("Mexican").get();

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
				.difficulty(Difficulty.MODERATE)
				.prepTime(60)
				.servings(4)
				.inCategories(american, mexican)
				.create();

		Recipe tacosRecipe = new Recipe();
		tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
		tacosRecipe.setCookTime(9);
		tacosRecipe.setPrepTime(20);
		tacosRecipe.setDifficulty(Difficulty.MODERATE);

		tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n"
				+ "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n"
				+ "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" + "\n"
				+ "\n"
				+ "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n"
				+ "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n"
				+ "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n"
				+ "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n"
				+ "\n" + "\n"
				+ "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

		Notes tacoNotes = new Notes();
		tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n"
				+ "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n"
				+ "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n"
				+ "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n"
				+ "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n"
				+ "\n" + "\n"
				+ "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");

		tacosRecipe.setNotes(tacoNotes);

		tacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoon));
		tacosRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teaSpoon));
		tacosRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), teaSpoon));
		tacosRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teaSpoon));
		tacosRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), teaSpoon));
		tacosRecipe.addIngredient(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), each));
		tacosRecipe.addIngredient(new Ingredient("finely grated orange zestr", new BigDecimal(1), tableSpoon));
		tacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tableSpoon));
		tacosRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tableSpoon));
		tacosRecipe.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), tableSpoon));
		tacosRecipe.addIngredient(new Ingredient("small corn tortillasr", new BigDecimal(8), each));
		tacosRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cup));
		tacosRecipe.addIngredient(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), each));
		tacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), each));
		tacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pint));
		tacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), each));
		tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), each));
		tacosRecipe.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cup));
		tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), each));

		tacosRecipe.getCategories().add(american);
		tacosRecipe.getCategories().add(mexican);

		recipeService.create(guacRecipe);
		recipeService.create(tacosRecipe);
	}

}
