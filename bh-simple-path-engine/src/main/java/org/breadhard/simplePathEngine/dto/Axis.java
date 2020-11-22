package org.breadhard.simplePathEngine.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection 
public class Axis implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2631762347111980793L;
	String id;
	String description;
	Boolean isValid;
	List<AxisPossibleValue> possibleValues;
	Date auditDate;
	String auditUser;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	public List<AxisPossibleValue> getPossibleValues() {
		return possibleValues;
	}
	public void setPossibleValues(List<AxisPossibleValue> possibleValues) {
		this.possibleValues = possibleValues;
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
		return "Axis [id=" + id + ", description=" + description + ", isValid=" + isValid + ", possibleValues="
				+ possibleValues + ", auditDate=" + auditDate + ", auditUser=" + auditUser + "]";
	}
}
