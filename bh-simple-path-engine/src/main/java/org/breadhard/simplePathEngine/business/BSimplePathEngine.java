package org.breadhard.simplePathEngine.business;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.breadhard.simplePathEngine.dao.DSimplePathEngine;
import org.breadhard.simplePathEngine.dto.AxisType;
import org.breadhard.simplePathEngine.dto.Operator;
import org.jboss.logging.Logger;

@ApplicationScoped
public class BSimplePathEngine {
	private static final Logger LOG = Logger.getLogger(BSimplePathEngine.class);
	
	@Inject
	DSimplePathEngine dao;
	
	public List<AxisType> getAxisTypes() throws Exception {
		return dao.getAxisTypes();
	}
	public List<Operator> getOperators() throws Exception {
		return dao.getOperators();
	}
}
