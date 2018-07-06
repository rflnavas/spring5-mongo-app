package guru.springframework.command;

import guru.springframework.model.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {
	private String id;
	private Recipe recipe;
	private String recipeNotes;
}
