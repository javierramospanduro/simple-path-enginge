package org.breadhard.simplePathEngine.utils;

public class Queries {
	public static final String getAllAxisTypes 
		= "SELECT TYPE_ID,TYPE_DESC FROM PARAM.AXIS_TYPES ORDER BY TYPE_ID";
	public static final String getAllOperators
		= "SELECT OPERATOR_ID,OPERATOR_DES FROM PARAM.OPERATORS ORDER BY OPERATOR_ID";
}
