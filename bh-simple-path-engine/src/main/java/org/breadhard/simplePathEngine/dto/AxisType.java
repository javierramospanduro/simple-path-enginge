package org.breadhard.simplePathEngine.dto;

import java.io.Serializable;
import java.sql.Date;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class AxisType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6172707926510934306L;
	/**
	 * 
	 */
	String id;
	String desc;
	Date auditDate;
	String auditUser;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
		return "AxisType [id=" + id + ", desc=" + desc + ", auditDate=" + auditDate + ", auditUser=" + auditUser + "]";
	}
}
