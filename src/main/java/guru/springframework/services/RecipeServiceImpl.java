package guru.springframework.services;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService{

	private RecipeRepository recipeRepository;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}
	
	@Override
	public Optional<Recipe> findById(Long id) {
		return recipeRepository.findById(id);
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
	
}
