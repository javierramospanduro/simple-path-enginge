package org.javi.engine.core.dto;

import java.io.Serializable;

import org.javi.engine.core.types.AxisType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Axis implements Serializable {
	
	private static final long serialVersionUID = 4705571101234736153L;
	String axisId;
	String description;
	AxisType type;
	public Axis(String id, String description, AxisType type) {
		super();
		this.axisId = id;
		this.description = description;
		this.type = type;
	}
	public Axis() {
		super();
	}	

}
