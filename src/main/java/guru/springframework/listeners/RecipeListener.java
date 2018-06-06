package guru.springframework.listeners;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

import guru.springframework.model.Recipe;

public class RecipeListener {
	
	@PrePersist
	public void recipeBeforePersist(Recipe recipe) {
		System.out.println("Recipe about to be persisted : " + recipe.getDescription());
		recipe.setCreatedOn(new Date(Calendar.getInstance().getTimeInMillis()));
	}
	
	@PostPersist
	public void recipeAfterPersist(Recipe recipe) {
		System.out.println("Recipe after being persisted with id : " + recipe.getId());
	}
}
