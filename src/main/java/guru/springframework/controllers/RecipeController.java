package guru.springframework.controllers;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.exception.NoModelFoundException;
import guru.springframework.model.Ingredient;
import guru.springframework.model.Recipe;
import guru.springframework.services.RecipeService;
import guru.springframework.utils.CollectionUtils;

@Controller
public class RecipeController {
	
	private RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@RequestMapping("/recipes/")
	public String showRecipes(Model model) {
		model.addAttribute("recipes", recipeService.getRecipes());
		return "recipes";
	}
	
	@RequestMapping(path = "/recipes/{id}")
	public String recipeDetail(Model model, @PathVariable(value = "id", required=true) Long id) {

		Recipe recipe = recipeService.findById(id)
				.orElseThrow(() -> new NoModelFoundException("Recipe has not been found"));
		checkRecipe(recipe);
		model.addAttribute("recipe", recipe);
		return "recipeDetail";
	}

	private void checkRecipe(Recipe recipe) {
		//Optional<Set<Ingredient>> ingredients = Optional.ofNullable(recipe.getIngredients());
		//if(ingredients.isPresent() && !ingredients.get().isEmpty())
				//ingredients.orElseThrow(()->new IllegalStateException("The recipe is faulty bacause ingredients are missing"));
		Set<Ingredient> ingredients=  recipe.getIngredients();
		if(CollectionUtils.isEmpty(ingredients)) {
			throw new IllegalStateException("The recipe is faulty bacause ingredients are missing");
		}
	}

}
