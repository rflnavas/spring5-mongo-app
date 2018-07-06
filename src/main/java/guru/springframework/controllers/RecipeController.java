package guru.springframework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.command.RecipeCommand;
import guru.springframework.command.converter.RecipeToRecipeCommand;
import guru.springframework.model.Recipe;
import guru.springframework.services.RecipeService;

@Controller
public class RecipeController {
	
	private RecipeService recipeService;

	RecipeToRecipeCommand recipeToRecipeCommand;
	
	@Autowired
	public RecipeController(RecipeService recipeService, RecipeToRecipeCommand recipeToRecipeCommand) {
		this.recipeService = recipeService;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}


	@GetMapping("/recipes/")
	public String showRecipes(Model model) {
		model.addAttribute("recipes", recipeService.getRecipes());
		return "recipe/recipes";
	}
	
	@GetMapping(path = "/recipes/{id}")
	public String recipeDetail(Model model, @PathVariable(value = "id", required=true) String id) {

		Recipe recipe = recipeService.findById(id);
		//checkRecipe(recipe);
		model.addAttribute("recipe", recipe);
		return "recipe/recipeDetail";
	}

//	private void checkRecipe(Recipe recipe) {
//		//Optional<Set<Ingredient>> ingredients = Optional.ofNullable(recipe.getIngredients());
//		//if(ingredients.isPresent() && !ingredients.get().isEmpty())
//				//ingredients.orElseThrow(()->new IllegalStateException("The recipe is faulty bacause ingredients are missing"));
//		Set<Ingredient> ingredients=  recipe.getIngredients();
//		if(CollectionUtils.isEmpty(ingredients)) {
//			throw new IllegalStateException("The recipe is faulty bacause ingredients are missing");
//		}
//	}
	
	/*
	 * the first parameter must be fed by the ModelAttribute annotation to 'catch' form values.
	 */
	@PostMapping()
	@RequestMapping("/recipes/")
	private String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		return "redirect:/recipes/" + savedCommand.getId();
	}
	
	@GetMapping("/recipes/new")
	private String newRecipe(Model model) {
		/*
		 * It is requested to create an empty command object to be bound to the form
		 */
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/recipeForm";
	}
	
	@GetMapping("/recipes/{id}/edit")
	private String editRecipe(Model model, @PathVariable("id") String id) {
		//Bad approach
		/*
		 * First we retrieve the persisted object from DB and then we cast it to a Command
		 */
		//Recipe recipe = recipeService.findById(id).orElseThrow(()->new NoModelFoundException("The recipe was not found"));
		//RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);
		model.addAttribute("recipe", recipeService.findCommandById(id));
		return "recipe/recipeForm";
	}

}
