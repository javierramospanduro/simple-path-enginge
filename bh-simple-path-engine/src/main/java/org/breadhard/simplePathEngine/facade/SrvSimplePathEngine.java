package org.breadhard.simplePathEngine.facade;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.breadhard.simplePathEngine.business.BSimplePathEngine;
import org.breadhard.simplePathEngine.dto.AxisType;
import org.breadhard.simplePathEngine.dto.Operator;
import org.jboss.logging.Logger;

@Path("/simple-path-engine")
public class SrvSimplePathEngine {
	
	private static final Logger LOG = Logger.getLogger(SrvSimplePathEngine.class);
	
	@Inject
	BSimplePathEngine business;
	
    @GET
    @Path("/params/axis/types")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAxisTypes() throws Exception {
    	LOG.info("Getting axis types");
    	List<AxisType> out = business.getAxisTypes();
    	if (out!=null && !out.isEmpty()) {
    		return Response.ok(out).build();
    	} else {
    		return Response.noContent().build();
    	}
    }
    @GET
    @Path("/params/operators")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getOperators() throws Exception {
    	LOG.info("Getting operators");
    	List<Operator> out = business.getOperators();
    	if (out!=null && !out.isEmpty()) {
    		return Response.ok(out).build();
    	} else {
    		return Response.noContent().build();
    	}
    }
}