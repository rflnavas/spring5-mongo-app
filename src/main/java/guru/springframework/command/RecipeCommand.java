package guru.springframework.command;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import guru.springframework.model.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
	
	private String id;
	private String description;
	private Set<IngredientCommand> ingredients = new LinkedHashSet<>();
	private Set<CategoryCommand> categories = new LinkedHashSet<>();
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions;
	private Difficulty difficulty;
	private NotesCommand notes;
	private byte[] image;
	private Date createdOn;
}
