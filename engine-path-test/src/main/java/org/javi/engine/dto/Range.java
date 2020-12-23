package org.javi.engine.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Range implements Serializable {
	private static final long serialVersionUID = -3990497619042664009L;
	BigDecimal minimum;
	BigDecimal maximum;
	public Range(BigDecimal minimum, BigDecimal maximum) {
		super();
		this.minimum = minimum;
		this.maximum = maximum;
	}
	
}
