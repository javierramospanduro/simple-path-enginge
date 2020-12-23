package org.javi.engine.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.javi.engine.types.AxisType;
import org.javi.engine.types.Operation;
import org.javi.engine.types.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Signal implements Serializable {

	private static final long serialVersionUID = 3830825825197373041L;
	private static final Log LOG = LogFactory.getLog(Signal.class);
	
	Axis axis;
	Operation operation;
	Response goodResponse;
	Response badResponse;
	List<Signal> nextSignals;
	Object value;
	boolean goodDirection;
	
	public Signal explore(Map<String,Object> indications) {
		LOG.info("Ready to explore!!");
		LOG.info("My data: " + this);
		LOG.info("Indications: " + indications);		
		boolean meetsCondition = isGoodDirection(indications);
		if (axis.getType() == AxisType.FINAL_AXIS) {
			LOG.info("This is the final axis, is good direction?" + meetsCondition);
			goodDirection = meetsCondition;
		} else {
			if (!meetsCondition) {
				LOG.info("This is not the final axis, and your direction is wrong");
				goodDirection = false;
			} else {
				LOG.info("This is not the final axis, and your direction is god, lets try next steps");
				for (Signal signal:nextSignals) {
					LOG.info("Explore signal: " + signal);
					signal.explore(indications);
					if (signal.isGoodDirection()) {
						LOG.info("Hey! this is good direction!");
						goodDirection = true;
						return this;
					}
				}
				LOG.info("I don't found any good direction, so, is bad direction :(");
				goodDirection = false;
			}
		}
		LOG.info("Ok, explored, is good direction? " + goodDirection);
		return this;
	}
	
	public boolean isGoodDirection(Map<String,Object> indications) {
		Object calculatedValue = indications.get(axis.getId());
		if (calculatedValue == null) {
			LOG.info("Value for the indication #" + axis +"# not found");
			return false;
		}
		LOG.info("Analyze operation " + operation + " whit calculatedValue: " + calculatedValue + " against value: " + value);
		switch (operation) { 
			case EQUALS:
				if (calculatedValue instanceof String) {
					return ((String)calculatedValue).equals((String)value);
				} else if (calculatedValue instanceof BigDecimal) {
					return ((BigDecimal)calculatedValue).compareTo((BigDecimal)value)==0;					
				} else {
					LOG.info("Type " + calculatedValue.getClass() + " don't support operation " + Operation.EQUALS);
				}
			case GREATER:
				if (calculatedValue instanceof BigDecimal) {
					return ((BigDecimal)calculatedValue).compareTo((BigDecimal)value)==1;					
				} else {
					LOG.info("Type " + calculatedValue.getClass() + " don't support operation " + Operation.GREATER);
				}
			case LOWER:
				if (calculatedValue instanceof BigDecimal) {
					return ((BigDecimal)calculatedValue).compareTo((BigDecimal)value)==-1;					
				} else {
					LOG.info("Type " + calculatedValue.getClass() + " don't support operation " + Operation.LOWER);
				}
			case RANGE:
				if (calculatedValue instanceof BigDecimal) {
					Range range = (Range) value;
					return ((BigDecimal) calculatedValue).compareTo(range.getMinimum()) <=0
							&& ((BigDecimal) calculatedValue).compareTo(range.getMaximum()) == 1;
				} else {
						LOG.info("Type " + calculatedValue.getClass() + " don't support operation " + Operation.RANGE);				
				}
			case CONTAINS:
				if (calculatedValue instanceof List) {
					return ((List<String>)calculatedValue).contains((String)value); 	
				}
			default:
				LOG.info("Operation " + operation + " not supported");
		}		
		return false;
	}

	public Signal(Axis axis, Operation operation, Response goodResponse, Response badResponse,
			boolean goodDirection,Object value) {
		super();
		this.axis = axis;
		this.operation = operation;
		this.goodResponse = goodResponse;
		this.badResponse = badResponse;
		this.goodDirection = goodDirection;
		this.value = value;
	}
	
	
}
