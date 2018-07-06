package guru.springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.model.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, String>{

}
