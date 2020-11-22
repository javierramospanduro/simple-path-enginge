package org.breadhard.simplePathEngine.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.breadhard.simplePathEngine.dto.AxisType;
import org.breadhard.simplePathEngine.dto.Operator;
import org.breadhard.simplePathEngine.mappers.AxisTypeMapper;
import org.breadhard.simplePathEngine.mappers.OperatorMapper;
import org.breadhard.simplePathEngine.utils.Queries;
import org.jboss.logging.Logger;

import io.agroal.api.AgroalDataSource;
import io.quarkus.agroal.DataSource;

@ApplicationScoped
public class DSimplePathEngine {
	private static final Logger LOG = Logger.getLogger(DSimplePathEngine.class);	
	
	@Inject
	@DataSource("enginedb")
	AgroalDataSource usersDataSource;
	
	@Inject
	AxisTypeMapper axisTypeMapper;
	@Inject
	OperatorMapper operatorMapper;
		
	public List<AxisType> getAxisTypes() throws Exception {
		LOG.info("Business - get axis types");
		Statement st = usersDataSource.getConnection().createStatement();
		ResultSet rs = st.executeQuery(Queries.getAllAxisTypes);
		List<AxisType> out = new ArrayList<>();
		while (rs.next()) {
			out.add(axisTypeMapper.mapFromResultSet(rs));
		}
		LOG.info(" Response: " + out);
		return out;	
	}
	public List<Operator> getOperators() throws Exception {
		LOG.info("Business - get operators");
		Statement st = usersDataSource.getConnection().createStatement();
		ResultSet rs = st.executeQuery(Queries.getAllOperators);
		List<Operator> out = new ArrayList<>();
		while (rs.next()) {
			out.add(operatorMapper.mapFromResultSet(rs));
		}
		LOG.info(" Response: " + out);
		return out;			
	}
}
