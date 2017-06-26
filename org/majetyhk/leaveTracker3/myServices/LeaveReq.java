package org.majetyhk.leaveTracker3.myServices;

import java.io.Serializable;


import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class LeaveReq implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int empId;
	public String startDate;
	public String endDate;
	public int startSession;
	public int endSession;
	
	public LeaveReq(){
		
	}
	
	
	/**
	 * @param empId
	 * @param startDate
	 * @param endDate
	 * @param startSession
	 * @param endSession
	 */
	public LeaveReq(int empId, String startDate, String endDate, int startSession, int endSession) {
		super();
		this.empId = empId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startSession = startSession;
		this.endSession = endSession;
	}


	public int getEmpId() {
		return empId;
	}


	public void setEmpId(int empId) {
		this.empId = empId;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public int getStartSession() {
		return startSession;
	}


	public void setStartSession(int startSession) {
		this.startSession = startSession;
	}


	public int getEndSession() {
		return endSession;
	}


	public void setEndSession(int endSession) {
		this.endSession = endSession;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
