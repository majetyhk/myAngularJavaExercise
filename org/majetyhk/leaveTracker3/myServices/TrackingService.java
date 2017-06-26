package org.majetyhk.leaveTracker3.myServices;


import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("trackingService")
public class TrackingService {
		
	@Path("myTest")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
	}

	@Path("getLeaveList/{monthVal}")
	@GET
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPeopleOnLeave(@PathParam("monthVal") int monthVal) throws ClassNotFoundException, SQLException {
		//String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost:3306/leaveTracker";

		// Database credentials
		String USER = "root";
		String PASS = "root";
		Connection conn = null;
		PreparedStatement stmt = null;
		List<Leave> leaveList = new ArrayList<>();
		GenericEntity<List <Leave>>  genericEntity;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			// System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			// System.out.println("Creating statement...");
			String sql = "SELECT * FROM leavelist INNER JOIN memberlist ON leaveList.empId=memberList.empId where (MONTH(leavelst.startDate)>=?) and (MONTH(endDate)<=?) ";
			
			System.out.println(sql);
			System.out.println(monthVal);
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, monthVal);
			stmt.setInt(2, monthVal);
			System.out.println(stmt);
			// Bind values into the parameters and Execute.
			ResultSet rs = stmt.executeQuery(sql);
			
			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int empId = rs.getInt("empId");
				int endSession = rs.getInt("endSession");
				int startSession = rs.getInt("startSession");
				int managerId = rs.getInt("managerId");
				String empName = rs.getString("empName");
				String startDate = rs.getString("startDate");
				String endDate = rs.getString("endDate");

				// Display values
				Leave leaveObj = new Leave(empId,empName,managerId,startDate,endDate,startSession,endSession);
				leaveObj.printLeaveDetails();
				leaveList.add(leaveObj);
				
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
			genericEntity = new GenericEntity<List<Leave>>(leaveList){};
			return Response.status(Status.OK).entity(genericEntity).build();
		} catch (ClassNotFoundException | SQLException se) {
			se.printStackTrace();
			int code = 13;
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(code).build();
		}
	}
	
	@Path("addLeaveRequest")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addLeaveRequest(LeaveReq leaveObj) throws ClassNotFoundException, SQLException {	
		
		//String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost:3306/leaveTracker";

		// Database credentials
		String USER = "root";
		String PASS = "root";
		Connection conn = null;
		PreparedStatement stmt = null;
		int empId = leaveObj.empId;
		String startDate = leaveObj.startDate;
		String endDate = leaveObj.endDate;
		int startSession = leaveObj.startSession;
		int endSession = leaveObj.endSession;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			// System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			// System.out.println("Creating statement...");
			String sql = "INSERT INTO leavelist(empId, startDate, endDate, startSession, endSession) VALUES(?,?,?,?,?) ";
			stmt = conn.prepareStatement(sql);
			
			//Bind values into the parameters.
		    stmt.setInt(1, empId);  // This would set empId
		    stmt.setString(2, startDate);
		    stmt.setString(3, endDate);
		    stmt.setInt(4, startSession);
		    stmt.setInt(5, endSession);
		    // Bind values into the parameters and Execute.
			int rs = stmt.executeUpdate();
			if(rs ==1 )
			{
				stmt.close();
				conn.close();
				//int code =3;
				return Response.status(Status.CREATED).build();
			}else {
				stmt.close();
				conn.close();
				//int code = 13;
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			}
		} catch(ClassNotFoundException | SQLException se) {
			se.printStackTrace();
			//int code = 13;
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Path("addMember")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMember(Member memberObj ) throws ClassNotFoundException, SQLException{
		//String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost:3306/leaveTracker";
		int empId = memberObj.empId;
		String empName = memberObj.empName;
		int managerId = memberObj.managerId;
		// Database credentials
		String USER = "root";
		String PASS = "root";
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			// System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			// System.out.println("Creating statement...");
			String sql = "INSERT INTO memberList(empId, empName, managerId) VALUES(?,?,?) ";
			stmt = conn.prepareStatement(sql);
			
			//Bind values into the parameters.
		    stmt.setInt(1, empId);  // This would set empId
		    stmt.setString(2, empName);
		    stmt.setInt(3, managerId);
			
		    // Bind values into the parameters and Execute.
			int rs = stmt.executeUpdate();
			if(rs ==1 )
			{
				//int code = 3;
				stmt.close();
				conn.close();
				return Response.status(Status.CREATED).build();
			}else {
				stmt.close();
				conn.close();
				//int code = 13;
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			}
		} catch(Exception se) {
			se.printStackTrace();
			//int code = 13;
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Path("getMembers")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMembers() throws ClassNotFoundException, SQLException {
		//String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost:3306/leaveTracker";

		// Database credentials
		String USER = "root";
		String PASS = "root";
		Connection conn = null;
		PreparedStatement stmt = null;
		List<Member> memberList = new ArrayList<>();
		GenericEntity<List <Member>>  genericEntity;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			// System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			// System.out.println("Creating statement...");
			String sql = "SELECT * FROM memberList";
			stmt = conn.prepareStatement(sql);

			// Bind values into the parameters and Execute.
			ResultSet rs = stmt.executeQuery(sql);
			
			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int empId = rs.getInt("empId");
//				int endSession = rs.getInt("endSession");
//				int startSession = rs.getInt("startSession");
				int managerId = rs.getInt("managerId");
				String empName = rs.getString("empName");
//				String startDate = rs.getString("startDate");
//				String endDate = rs.getString("endDate");

				// Display values
				Member memberObj = new Member(empId,empName,managerId);
				//leaveObj.printLeaveDetails();
				memberList.add(memberObj);
				
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
			genericEntity = new GenericEntity<List<Member>>(memberList){};
			return Response.status(Status.OK).entity(genericEntity).build();
		} catch (ClassNotFoundException | SQLException se) {
			se.printStackTrace();
			//int code = 13;
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
