package org.breadhard.simplePathEngine.mappers;

import java.sql.ResultSet;

import javax.enterprise.context.ApplicationScoped;

import org.breadhard.simplePathEngine.dto.Operator;
@ApplicationScoped
public class OperatorMapper {
	public Operator mapFromResultSet(ResultSet in) throws Exception {
		Operator out = new Operator();
		out.setId(in.getString(1));
		out.setDescription(in.getString(2));
		return out;
	}
}
