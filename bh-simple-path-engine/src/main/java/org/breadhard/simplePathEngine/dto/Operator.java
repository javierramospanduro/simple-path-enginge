package org.breadhard.simplePathEngine.dto;

import java.io.Serializable;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Operator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5672992144209098884L;
	String id;
	String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Operator [id=" + id + ", description=" + description + "]";
	}

}
