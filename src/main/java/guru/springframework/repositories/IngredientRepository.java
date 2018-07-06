package guru.springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.model.Ingredient;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String>{

}
