package guru.springframework.model;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

/*
 * The first we did is erasing all JPA related annotations so that we take a closer step to Mongo
 *
 */
@Getter
@Setter
@Document
public class Recipe {
	
	/*
	 *	id data type changed! 
	 */
	@Id
	private String id;
	private String description;
	private Set<Ingredient> ingredients = new LinkedHashSet<>();
	@DBRef
	private Set<Category> categories = new LinkedHashSet<>();
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions;
	private Difficulty difficulty;
	private Notes notes;
	private byte[] image;
	private Date createdOn;

	public Recipe() {
	}

	public void withNotes(String recipeNote) {
		this.notes = new Notes();
		this.notes.setRecipeNotes(recipeNote);
		//this.notes.setRecipe(this);
	}
	
	public void addIngredient(Ingredient ingredient) {
		//ingredient.setRecipe(this);
		this.ingredients.add(ingredient);
	}
	
	@Override
	public String toString() {
		return "Recipe [description=" + description + ", prepTime=" + prepTime + ", cookTime=" + cookTime
				+ ", servings=" + servings + ", source=" + source + ", url=" + url + ", directions=" + directions
				+ ", notes=" + notes + ", image=" + Arrays.toString(image) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public static class RecipeBuilder{
		
		private String description;
		
		private Set<Ingredient> ingredients;
		
		private Set<Category> categories;
		
		private Difficulty difficulty;
		
		private Integer prepTime;
		
		private Integer servings;

		private String recipeNotes;
		
		private String directions;
		
		private RecipeBuilder() {
			ingredients = new LinkedHashSet<>();
			categories = new LinkedHashSet<>();
		}
		
		public static RecipeBuilder of() {
			return new RecipeBuilder();
		}
		
		public RecipeBuilder description(String description) {
			this.description = description;
			return this;
		}
		
		public RecipeBuilder difficulty(Difficulty difficulty) {
			this.difficulty = difficulty;
			return this;
		}
		
		public RecipeBuilder prepTime(Integer prepTime) {
			this.prepTime = prepTime;
			return this;
		}
		
		public RecipeBuilder servings(Integer servings) {
			this.servings = servings;
			return this;
		}
		
		public RecipeBuilder notes(String recipeNotes) {
			this.recipeNotes = recipeNotes;
			return this;
		}
		
		public RecipeBuilder directions(String directions) {
			this.directions = directions;
			return this;
		}
		
		
//		public RecipeBuilder ingredients(Set<Ingredient> ingredients) {
//			this.ingredients = ingredients;
//			return this;
//		}
		
		public Recipe create(){
			final Recipe recipe = new Recipe();
			recipe.setDescription(description);
			//recipe.setIngredients(ingredients);
//			this.ingredients.forEach(i -> i.setRecipe(recipe));
			recipe.setIngredients(this.ingredients);
			this.categories.forEach(c -> recipe.getCategories().add(c));
			recipe.setCategories(this.categories);
			recipe.setDifficulty(difficulty);
			recipe.setPrepTime(prepTime);
			recipe.setServings(servings);
			recipe.withNotes(recipeNotes);
			recipe.setDirections(directions);
			return recipe;
		}

		public RecipeBuilder withIngredients(Ingredient... ingredients) {
			Arrays.stream(ingredients).forEach(i -> RecipeBuilder.this.ingredients.add(i));
			return this;
		}
		
		public RecipeBuilder inCategories(Category... categories) {
			Arrays.stream(categories).forEach(c -> RecipeBuilder.this.categories.add(c));
			return this;
		}
	}

}
