package guru.springframework.model;

import java.util.Date;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import guru.springframework.listeners.RecipeListener;

@Entity
@EntityListeners({RecipeListener.class})
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Ingredient> ingredients = new LinkedHashSet<>();
	/**
	 * Who is the owner(joinColumns) the relationship and who is the owned(inverseJoinColumns) one?
	 */
	@ManyToMany
	@JoinTable(name = "recipe_category", joinColumns = { @JoinColumn(name = "recipe_id")},
		inverseJoinColumns= {@JoinColumn(name="category_id")})
	private Set<Category> categories = new LinkedHashSet<>();
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	@Lob
	private String directions;
	@Enumerated(value = EnumType.STRING)
	private Difficulty difficulty;
	@OneToOne(cascade = CascadeType.ALL)
	private Notes notes;
	@Lob
	private byte[] image;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	public Recipe() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(Integer prepTime) {
		this.prepTime = prepTime;
	}

	public Integer getCookTime() {
		return cookTime;
	}

	public void setCookTime(Integer cookTime) {
		this.cookTime = cookTime;
	}

	public Integer getServings() {
		return servings;
	}

	public void setServings(Integer servings) {
		this.servings = servings;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Notes getNotes() {
		return notes;
	}

	public void setNotes(Notes notes) {
		this.notes = notes;
		notes.setRecipe(this);
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void addIngredient(Ingredient ingredient) {
		ingredient.setRecipe(this);
		this.ingredients.add(ingredient);
	}

	public void withNotes(String recipeNote) {
		this.notes = new Notes();
		this.notes.setRecipeNotes(recipeNote);
		this.notes.setRecipe(this);
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
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
			this.ingredients.forEach(i -> i.setRecipe(recipe));
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
