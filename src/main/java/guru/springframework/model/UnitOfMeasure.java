package guru.springframework.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class UnitOfMeasure {
	
	@Id
	private String id;
	private String measure;
	public UnitOfMeasure() {
	}
	
	public UnitOfMeasure(String measure) {
		super();
		this.measure = measure;
	}

	@Override
	public String toString() {
		return "UnitOfMeasure [measure=" + measure + "]";
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
		UnitOfMeasure other = (UnitOfMeasure) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
