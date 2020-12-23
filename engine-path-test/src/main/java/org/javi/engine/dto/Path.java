package org.javi.engine.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter @ToString
public class Path implements Serializable {
	
	private static final long serialVersionUID = 5542136635683088962L;
	private static final Log LOG = LogFactory.getLog(Signal.class);
	
	Axis rootAxis;
	Object rootValue;
	List<Signal> signals;
	
	
}
