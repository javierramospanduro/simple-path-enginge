package org.breadhard.simplePathEngine.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class AxisPossibleValue implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String axisId;
	String typeId;
	BigDecimal id;
	Boolean isValid;
	Boolean booleanValue;
	BigDecimal decimalValue;
	String stringValue;
	Date auditDate;
	String auditUser;
	public String getAxisId() {
		return axisId;
	}
	public void setAxisId(String axisId) {
		this.axisId = axisId;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	public Boolean getBooleanValue() {
		return booleanValue;
	}
	public void setBooleanValue(Boolean booleanValue) {
		this.booleanValue = booleanValue;
	}
	public BigDecimal getDecimalValue() {
		return decimalValue;
	}
	public void setDecimalValue(BigDecimal decimalValue) {
		this.decimalValue = decimalValue;
	}
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	public String getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AxisPossibleValue [axisId=" + axisId + ", typeId=" + typeId + ", id=" + id + ", isValid=" + isValid
				+ ", booleanValue=" + booleanValue + ", decimalValue=" + decimalValue + ", stringValue=" + stringValue
				+ ", auditDate=" + auditDate + ", auditUser=" + auditUser + "]";
	}
}
