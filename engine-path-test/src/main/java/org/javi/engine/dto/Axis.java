package org.javi.engine.dto;

import java.io.Serializable;

import org.javi.engine.types.AxisType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Axis implements Serializable {
	
	private static final long serialVersionUID = 4705571101234736153L;
	String id;
	String description;
	AxisType type;
	public Axis(String id, String description, AxisType type) {
		super();
		this.id = id;
		this.description = description;
		this.type = type;
	}
	public Axis() {
		super();
	}	

}
