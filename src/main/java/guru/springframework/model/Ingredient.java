package guru.springframework.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Recipe recipe;
	private String description;
	private BigDecimal amount;
	/*
	 * Although it is implicit, I want to highlight it.
	 */
	@OneToOne(fetch=FetchType.EAGER)
	private UnitOfMeasure unitOfMeasure;
	
	public Ingredient() {
	}
	
	public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure) {
		super();
		this.description = description;
		this.amount = amount;
		this.unitOfMeasure = unitOfMeasure;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	

	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", recipe=" + recipe + ", description=" + description + ", amount=" + amount
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
