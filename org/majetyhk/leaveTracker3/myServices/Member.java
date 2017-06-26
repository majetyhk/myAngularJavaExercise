package org.majetyhk.leaveTracker3.myServices;

import java.io.Serializable;


import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Member implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int empId;
	public String empName;
	public int managerId;
	
	public Member(){
		
	}
	
	/**
	 * @param empID
	 * @param empName
	 * @param managerId
	 */
	
	public Member(int empId, String empName, int managerId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.managerId = managerId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
