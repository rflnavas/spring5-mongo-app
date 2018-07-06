package guru.springframework.services;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import guru.springframework.command.RecipeCommand;
import guru.springframework.command.converter.RecipeCommandToRecipe;
import guru.springframework.command.converter.RecipeToRecipeCommand;
import guru.springframework.exception.NoModelFoundException;
import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService{

	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe,
			RecipeToRecipeCommand recipeToRecipeCommand) {
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}

	@Override
	public Recipe findById(String id) {
		Recipe recipe = recipeRepository.findById(id).orElseThrow(()->new NoModelFoundException("No recipe found"));
		return recipe;
	}

	@Override
	public void create(Recipe recipe) {
		recipeRepository.save(recipe);
	}
	
	public Set<Recipe> getRecipes(){
		Set<Recipe> recipes = new LinkedHashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(r -> recipes.add(r));
		return recipes;
	}

	@Override
	public void update(Recipe recipe) {
		recipeRepository.save(recipe);
	}
	
	@Override
	public void delete(Recipe recipe) {
		recipeRepository.delete(recipe);
	}

	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand command) {
		//Convert a recipe form from the webpage to a Recipe instance.
		Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
		//Note that the Recipe above is not in the Hibernate context so we save it.
		Recipe savedRecipe = recipeRepository.save(detachedRecipe);
		log.debug(">>> Saved recipeId:" + savedRecipe.getId());
		return recipeToRecipeCommand.convert(savedRecipe);
	}

	@Override
	@Transactional
	public RecipeCommand findCommandById(String id) {
		Recipe recipe = this.findById(id);
		return recipeToRecipeCommand.convert(recipe);
	}
	
	
}
