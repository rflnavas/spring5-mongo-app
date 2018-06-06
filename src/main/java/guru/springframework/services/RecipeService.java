package guru.springframework.services;

import java.util.Optional;
import java.util.Set;

import guru.springframework.model.Recipe;

public interface RecipeService {
	
	void create(Recipe recipe);
	Optional<Recipe> findById(Long id);
	Set<Recipe> getRecipes();
}
