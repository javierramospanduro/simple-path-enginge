package org.breadhard.simplePathEngine.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.enterprise.context.ApplicationScoped;

import org.breadhard.simplePathEngine.dto.AxisType;


@ApplicationScoped
public class AxisTypeMapper {
	public AxisType mapFromResultSet(ResultSet in) throws SQLException {
		AxisType out = new AxisType();
		out.setId(in.getString(1));
		out.setDesc(in.getString(2));
		return out;		
	}

}
