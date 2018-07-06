package guru.springframework.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ingredient {
	@Id
	private String id;
	/*
	 * Because of recipe has a reference to a collection of ingredients, each
	 * ingredient points to the same recipe before and at the same time this same
	 * recipe points to such ingredientes and so on. Thus, there is a cycle which 
	 * never ends and provokes a StackOverFlowException
	 */
//	private Recipe recipe;
	private String description;
	private BigDecimal amount;
	@DBRef
	private UnitOfMeasure unitOfMeasure;
	
	public Ingredient() {
	}
	
	public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure) {
		super();
		this.description = description;
		this.amount = amount;
		this.unitOfMeasure = unitOfMeasure;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", description=" + description + ", amount=" + amount
				+ ", unitOfMeasure=" + unitOfMeasure + "]";
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
		Ingredient other = (Ingredient) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public static class IngredientBuilder{
		private BigDecimal amount;
		private String description;
		private UnitOfMeasure unitOfMeasure;
		
		
		private IngredientBuilder() {
		}
		
		public static IngredientBuilder of() {
			return new IngredientBuilder();
		}
		
		public IngredientBuilder description(String description) {
			this.description = description;
			return this;
		}
		
		public IngredientBuilder amount(BigDecimal amount) {
			this.amount = amount;
			return this;
		}
		
		public IngredientBuilder amount(int amount) {
			this.amount = new BigDecimal(amount);
			return this;
		}
		
		public IngredientBuilder unitOfMeasure(UnitOfMeasure unitOfMeasure) {
			this.unitOfMeasure = unitOfMeasure;
			return this;
		}
		
		public Ingredient create(){
			Ingredient ingredient = new Ingredient();
			ingredient.setDescription(description);
			ingredient.setAmount(amount);
			ingredient.setUnitOfMeasure(unitOfMeasure);
			return ingredient;
		}
	}

}
