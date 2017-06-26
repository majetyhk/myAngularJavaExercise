package org.majetyhk.leaveTracker3.myServices;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Leave implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int empID;
	public String empName;
	public int managerId;
	public String startDate;
	public String endDate;
	public int startSession;
	public int endSession;
	
	/**
	 * @param empID
	 * @param empName
	 * @param manager
	 * @param startDate
	 * @param endDate
	 * @param startSession
	 * @param endSession
	 */
	public Leave(){
		
	}

	
	/**
	 * @param empID
	 * @param empName
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @param startSession
	 * @param endSession
	 */
	
	public Leave(int empID, String empName, int managerId,String startDate, String endDate, int startSession,
			int endSession) {
		super();
		this.empID = empID;
		this.empName = empName;
		this.managerId = managerId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startSession = startSession;
		this.endSession = endSession;
	}


	public int getEmpID() {
		return empID;
	}


	public void setEmpID(int empID) {
		this.empID = empID;
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


	public void printLeaveDetails(){
		System.out.print("ID: " + empID);
		System.out.println(", empName " + empName);
		System.out.print(", startDate " + startDate);
		System.out.print(", EndDate " + endDate);
		System.out.print("startSession: " + startSession);
		System.out.print("endSession: " + endSession);
		System.out.print("ManagerId: " + managerId);
	}
	
	
}
