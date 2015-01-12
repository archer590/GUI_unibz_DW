package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


public class DBConnection {
	
	private static Connection connectionDBusers = null;
	private static Connection conn = null;
    private String dbname = null;
    private String username = null;
    private String password = null;
    private boolean correct = true;
    public Vector<InOutcoming_Student> inOut_students = new Vector<InOutcoming_Student>();
    public Vector<Enrollment> enrolled = new Vector<Enrollment>();
    
    private String name = null;
    
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDbname() {
        return dbname;
    }

    public void setDbname(String databaseName) {
    	dbname = databaseName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
    	username = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
    	password = pass;
    }
    
    public void setLogin(boolean response){
    	correct = response;
    }
    
    public boolean getLogin(){
    	return correct;
    }

    // connect to the database
    public boolean getDBConnection() throws Exception{
        String host = "postgresql.admt2014.alwaysdata.net:";
		String port = "5432";
		try {
			Class.forName("org.postgresql.Driver");
			String connectionUrl1 = "jdbc:postgresql://" + host + port + "/" + "admt2014_users";
			connectionDBusers = DriverManager.getConnection(connectionUrl1, "admt2014", "admt");
			
			Statement stmt = connectionDBusers.createStatement();            
            ResultSet rs = stmt.executeQuery("SELECT COUNT(user_key) AS ussdw FROM user_dw WHERE username ='"+username+"' AND password ='"+password+"'");

            int numberUser = 0;
            while (rs.next()){
            	numberUser = Integer.parseInt(rs.getString("ussdw"));           	
            }

			if (numberUser==1){
				
				Statement stmt2 = connectionDBusers.createStatement();            
	            ResultSet rs2 = stmt2.executeQuery("SELECT name FROM user_dw WHERE username ='"+username+"' AND password ='"+password+"'");
	            while (rs2.next()){
	            	setName(rs2.getString("name"));           	
	            }
	            closeDBusersConnection();
	            String connectionUrl = "jdbc:postgresql://" + host + port + "/" + "admt2014_uniwarehouse";
				conn = DriverManager.getConnection(connectionUrl, "admt2014", "admt");
				
				return true;
			}
			else 
				setLogin(false);
			
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
    }
    
    // disconnect from the database
    public void closeDBusersConnection() throws SQLException{
    	connectionDBusers.close();
    }
    
    /*ROLLUP Query*/
    public Vector<InOutcoming_Student> rollup_incoming_students(){
    	if(!inOut_students.isEmpty())
			inOut_students.clear();
        	try {
        		Statement stmt = conn.createStatement();            
                ResultSet rs = stmt.executeQuery("select cd.faculty, cd.study_plan, cd.curriculum_name, count(ef.student_key) from ((admt2014_unibzdw.erasmus_fact ef join admt2014_unibzdw.student_dimension sd on ef.student_key = sd.student_key) join admt2014_unibzdw.curriculum_dimension cd on sd.curriculum_key = cd.curriculum_key) join admt2014_unibzdw.university_dimension ud on ef.university_key = ud.university_key where ud.university_name = 'Betty' group by cd.faculty, cd.study_plan, cd.curriculum_name union select cd.faculty, cd.study_plan, NULL, count(ef.student_key) from ((admt2014_unibzdw.erasmus_fact ef join admt2014_unibzdw.student_dimension sd on ef.student_key = sd.student_key) join admt2014_unibzdw.curriculum_dimension cd on sd.curriculum_key = cd.curriculum_key) join admt2014_unibzdw.university_dimension ud on ef.university_key = ud.university_key where ud.university_name = 'Betty' group by cd.faculty, cd.study_plan union all select cd.faculty, NULL, NULL, count(ef.student_key) from ((admt2014_unibzdw.erasmus_fact ef join admt2014_unibzdw.student_dimension sd on ef.student_key = sd.student_key) join admt2014_unibzdw.curriculum_dimension cd on sd.curriculum_key = cd.curriculum_key) join admt2014_unibzdw.university_dimension ud on ef.university_key = ud.university_key where ud.university_name = 'Betty' group by cd.faculty union all select NULL, NULL, NULL, count(ef.student_key) from ((admt2014_unibzdw.erasmus_fact ef join admt2014_unibzdw.student_dimension sd on ef.student_key = sd.student_key) join admt2014_unibzdw.curriculum_dimension cd on sd.curriculum_key = cd.curriculum_key) join admt2014_unibzdw.university_dimension ud on ef.university_key = ud.university_key where ud.university_name = 'Betty' order by curriculum_name, study_plan, faculty;");
                
                while (rs.next()){
                	String faculty = rs.getString("faculty");
    				String study_plan = rs.getString("study_plan");
    				String curriculum_name = rs.getString("curriculum_name");
                	int count = Integer.parseInt(rs.getString("count"));
                	inOut_students.add(new InOutcoming_Student(faculty, study_plan, curriculum_name, count));
                }
            } catch (SQLException e) {System.out.println(inOut_students.size());System.out.println(e.getMessage()+e.getLocalizedMessage());}
            return inOut_students;
        }
    
    public Vector<InOutcoming_Student> rollup_outgoing_students(){
		if(!inOut_students.isEmpty())
			inOut_students.clear();
    	try {
    		Statement stmt = conn.createStatement();            
            ResultSet rs = stmt.executeQuery("select cd.faculty, cd.study_plan, cd.curriculum_name, count(ef.student_key) from ((admt2014_unibzdw.erasmus_fact ef join admt2014_unibzdw.student_dimension sd on ef.student_key = sd.student_key) join admt2014_unibzdw.curriculum_dimension cd on sd.curriculum_key = cd.curriculum_key) join admt2014_unibzdw.university_dimension ud on ef.university_key = ud.university_key where ud.university_name != 'Betty' group by cd.faculty, cd.study_plan, cd.curriculum_name union select cd.faculty, cd.study_plan, NULL, count(ef.student_key) from ((admt2014_unibzdw.erasmus_fact ef join admt2014_unibzdw.student_dimension sd on ef.student_key = sd.student_key) join admt2014_unibzdw.curriculum_dimension cd on sd.curriculum_key = cd.curriculum_key) join admt2014_unibzdw.university_dimension ud on ef.university_key = ud.university_key where ud.university_name != 'Betty' group by cd.faculty, cd.study_plan union all select cd.faculty, NULL, NULL, count(ef.student_key) from ((admt2014_unibzdw.erasmus_fact ef join admt2014_unibzdw.student_dimension sd on ef.student_key = sd.student_key) join admt2014_unibzdw.curriculum_dimension cd on sd.curriculum_key = cd.curriculum_key) join admt2014_unibzdw.university_dimension ud on ef.university_key = ud.university_key where ud.university_name != 'Betty' group by cd.faculty union all select NULL, NULL, NULL, count(ef.student_key) from ((admt2014_unibzdw.erasmus_fact ef join admt2014_unibzdw.student_dimension sd on ef.student_key = sd.student_key) join admt2014_unibzdw.curriculum_dimension cd on sd.curriculum_key = cd.curriculum_key) join admt2014_unibzdw.university_dimension ud on ef.university_key = ud.university_key where ud.university_name != 'Betty' order by curriculum_name, study_plan, faculty;");
            
            while (rs.next()){
            	String faculty = rs.getString("faculty");
				String study_plan = rs.getString("study_plan");
				String curriculum_name = rs.getString("curriculum_name");
            	int count = Integer.parseInt(rs.getString("count"));
            	inOut_students.add(new InOutcoming_Student(faculty, study_plan, curriculum_name, count));
            }
        } catch (SQLException e) {System.out.println(inOut_students.size());System.out.println(e.getMessage()+e.getLocalizedMessage());}
        return inOut_students;
    }
   
    public Vector<Enrollment> camparison_enrollment_year(){
		if(!enrolled.isEmpty())
			enrolled.clear();
    	try {
    		Statement stmt = conn.createStatement();            
            ResultSet rs = stmt.executeQuery("SELECT count(sd.student_key) as students, sd.enrollment_year FROM admt2014_unibzdw.student_dimension sd where sd.enrollment_year='2014' or sd.enrollment_year='2013' group by sd.enrollment_year union all SELECT (SELECT count(ssd.student_key) FROM admt2014_unibzdw.student_dimension ssd where ssd.enrollment_year='2013') - (SELECT count(ssd.student_key) FROM admt2014_unibzdw.student_dimension ssd where ssd.enrollment_year='2014'), NULL;");
            
            while (rs.next()){
            	int students = rs.getInt("students");
				int year = rs.getInt("enrollment_year");				
            	enrolled.add(new Enrollment(year, students));
            }
        } catch (SQLException e) {System.out.println(enrolled.size());System.out.println(e.getMessage()+e.getLocalizedMessage());}
        return enrolled;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*Query 1*/
    public Vector<String> selectAllDoctors(){
    		Vector<String> allDoctors = new Vector<String>();
        	try {
        		Statement stmt = connectionDBusers.createStatement();            
                ResultSet rs = stmt.executeQuery("SELECT SSN, FirstName, LastName, Speciality FROM Doctor");
                while (rs.next()){
                	String FirstName = rs.getString("FirstName");
    				String LastName = rs.getString("LastName");
    				String Speciality = rs.getString("Speciality");
                	String SSN = rs.getString("SSN");
                	allDoctors.add(SSN+ " " + FirstName +" "+ LastName+ " "+ "Specialized in "+Speciality);
                }
            } catch (SQLException e) {System.out.println(e);}
            return allDoctors;
        }
    

    
//    public Vector<String> giveDepartmentByFloor(String floor1, String floor2){
//    	System.out.println("floor1: "+floor1+" floor2: "+floor2);
//		Vector<String> departmentByFloor = new Vector<String>();
//    	try {
//    		Statement stmt = conn.createStatement();   
//            ResultSet rs = stmt.executeQuery("SELECT D.name" +
//            		" FROM DEPARTMENT D" +
//            		" WHERE NOT EXISTS( select S.DEP_Name "+
//          				  "FROM SITUATED S"+
//          				  "WHERE ( f_floornumber ="+ floor1 +"" +
//          				  			"OR" +
//          						 "[f_floornumber="+ floor2 +"]) AND"+
//          						 "(NOT EXISTS ( select D1.name"+
//          									   "from DEPARTMENT D1"+
//          									   "where D1.name = S.DEP_NAME AND"+
//          									  " D1.name = D.name)))");
//            while (rs.next()){
//            	String dep = rs.getString("name");
//            	
//            	departmentByFloor.add("The Department of " +dep+ " is situated in "+floor1+" "+floor2+" and  is the director.");
//            }
//        } catch (SQLException e) {System.out.println(e);}
//        return departmentByFloor;
//    }
//    
//    /*Query 3
//     * select P.FirstName, P.LastName
//	from Patient P
//	where NOT EXISTS ( select A.PSSN
//				   from Account A
//				   where ( A.Amount > 500) AND
//				   (NOT EXISTS ( select P1.SSN
//								 from Patient P1
//								 where P1.SSN = P.SSN AND
//									   P1.SSN = A.PSSN)))
//     * */
//    public Vector<String> givePatientByAccount(String bal){
//		Vector<String> patientByAccount = new Vector<String>();
//    	try {
//    		Statement stmt = conn.createStatement();            
//            ResultSet rs = stmt.executeQuery("SELECT P.FirstName, P.LastName" +
//            		" FROM Patient P" +
//            		" WHERE NOT EXISTS( select A.PSSN "+
//          				  "FROM Account A"+
//          				  "WHERE ( A.Amount > '"+ bal +"' )"+
//          						"AND("+
//          						"NOT EXISTS ( select	P1.SSN"+
//          									 "from Patient P1"+
//          									 "where P1.SSN = P.SSN AND"+
//          										"P1.SSN = A.PSSN )))");
//            while (rs.next()){
//            	String fname = rs.getString("P.FirstName");
//            	String lname = rs.getString("P.LastName");
//            	patientByAccount.add(fname+ " "+lname+" "+" has an acount balance more than" +bal);
//            }
//        } catch (SQLException e) {System.out.println(e);}
//        return patientByAccount;
//    }
//    
    /* Query 4*/
    public String countPatientByRoom(String room){
    	String patientbyRoom = null;
    		//Vector<String> patientByRoom = new Vector<String>();
        	try {
        		Statement stmt = connectionDBusers.createStatement();            
                ResultSet rs = stmt.executeQuery("SELECT COUNT(SSN) AS asd FROM Patient WHERE R_RoomNumber ='"+room+"'");
                while (rs.next()){
                	String count = rs.getString("asd");
                	patientbyRoom="There are "+count+" patient in room "+room+".";
                }
            } catch (SQLException e) {System.out.println(e);}
            return patientbyRoom;
        }

    /* Query 5 */
    public Vector<String> selectPatientbyAge(String age){
    		Vector<String> patientByAge = new Vector<String>();
        	try {
        		Statement stmt = connectionDBusers.createStatement();            
                ResultSet rs = stmt.executeQuery("SELECT FirstName, LastName FROM Patient WHERE Age >'"+age+"'");
                while (rs.next()){
                	String fname = rs.getString("FirstName");
                	String lname = rs.getString("LastName");
    				patientByAge.add("Patient "+fname+" "+lname+" "+"has age more then "+age);
                }
            } catch (SQLException e) {System.out.println(e);}
            return patientByAge;
        }
    	
    /*Query 6*/
    public Vector<String> selectPatientsByDepartment(String department){
    		Vector<String> patientsByDepartment = new Vector<String>();
        	try {
        		Statement stmt = connectionDBusers.createStatement();          
                ResultSet rs = stmt.executeQuery("SELECT FirstName, LastName FROM Patient WHERE DEP_Name='"+department+"'");
                while (rs.next()){
                	String fname = rs.getString("FirstName");
                	String lname = rs.getString("LastName");
                	patientsByDepartment.add("Patient : "+fname+" " + lname + " is admmitted in "+department);
                }
            } catch (SQLException e) {System.out.println(e);}
            return patientsByDepartment;
        }

    	/*Query 7*/
    	public Vector<String> countPatientByDep(String department){
    		Vector<String> patientByDep = new Vector<String>();
        	try {
        		Statement stmt = connectionDBusers.createStatement();            
                ResultSet rs = stmt.executeQuery("SELECT count(SSN) as asd FROM Patient WHERE DEP_Name ='"+department+"'");
                while (rs.next()){
                	String count = rs.getString("asd");
                	patientByDep.add("There are "+ count + " patient in - " +department + " department.");
                }
            } catch (SQLException e) {System.out.println(e);}
            return patientByDep;
        }
    	
    /*Query 8a*/
    public Vector<String> selectHasAccount(){
    		Vector<String> hasAccount = new Vector<String>();
        	try {
        		Statement stmt = connectionDBusers.createStatement();            
                ResultSet rs = stmt.executeQuery("SELECT FirstName, LastName FROM Patient AS p, Account AS a WHERE p.SSN = a.PSSN");
                while (rs.next()){
                	String fname = rs.getString("FirstName");
                	String lname = rs.getString("LastName");
                	hasAccount.add(fname + " " + lname + " has an account ");
                }
            } catch (SQLException e) {System.out.println(e);}
            return hasAccount;
        }


    /* Query 8b
    	select SSN, FirstName, LastName
    	from Paitent

    	except

    	select SSN, FirstName, LastName
    	form Patient P , Account A
    	where P.SSN = A.PSSN

    	select patient don't have an account*

    	
    		public Vector<String> selectNoaccount(){
    			Vector<String> noAccount = new Vector<String>();
    	    	try {
    	    		Statement stmt = conn.createStatement();            
    	            ResultSet rs = stmt.executeQuery("SELECT p.SSN, p.FirstName, p.LastName" +
    	            		" FROM Patient p" +
    	            		" except"+
    						" SELECT p.SSN, p.FirstName, p.LastName"+
    						" FROM Patient AS p, Account AS a" +
    						"WHERE p.SSN = a.PSSN ");
    	            while (rs.next()){
    	            	String fname = rs.getString("p.FirstName");
    	            	String lname = rs.getString("p.LastName");
    	            	String ssn = rs.getString("p.SSN");
    	            	noAccount.add(fname+" "+lname+" has no account and his / her ssn is "+ssn);
    	            }
    	        } catch (SQLException e) {System.out.println(e);}
    	        return noAccount;
    	    }*/

    	/*Query 9*/
    		public Vector<String> selectDoctor(String sex){
    			Vector<String> doctor = new Vector<String>();
    	    	try {
    	    		Statement stmt = connectionDBusers.createStatement();            
    	            ResultSet rs = stmt.executeQuery("SELECT * FROM Doctor WHERE Sex ='"+sex+"'");
    	            while (rs.next()){
    	            	String SSN = rs.getString("SSN");
    	            	String fname = rs.getString("FirstName");
    					String lname = rs.getString("LastName");
    					String address = rs.getString("Address");
    					String bdate = rs.getString("Birthdate");
    					String spe = rs.getString("Speciality");
    					String dep = rs.getString("DEP_Name");
    					doctor.add(SSN+" "+fname+" " +lname+" "+address+" "+bdate+" "+spe+ " "+dep);
    					;
    	            }
    	        } catch (SQLException e) {System.out.println(e);}
    	        return doctor;
    	    }
    		
    	/*Query 10*/
	    public Vector<String> selectPatientByRoom(String room){
	    		Vector<String> patientByRoom= new Vector<String>();
	        	try {
	        		Statement stmt = connectionDBusers.createStatement();            
	                ResultSet rs = stmt.executeQuery("SELECT FirstName, LastName FROM Patient WHERE R_RoomNumber = '"+room+"'");
	                while (rs.next()){
	                	String fname = rs.getString("FirstName");
	                	String lname = rs.getString("LastName");
	                   	patientByRoom.add(fname+" "+lname+" staying in room - "+room);
	                }
	            } catch (SQLException e) {System.out.println(e);}
	            return patientByRoom;
	        }
    	
    	/*Query 11*/
	    public Vector<String> selectDoctorBySpe(String speciality){
	    		Vector<String> doctorBySpe= new Vector<String>();
	        	try {
	        		Statement stmt = connectionDBusers.createStatement();            
	                ResultSet rs = stmt.executeQuery("SELECT FirstName, LastName FROM Doctor WHERE Speciality = '"+speciality+"'");
	                while (rs.next()){
	                	String fname = rs.getString("FirstName");
	                	String lname = rs.getString("LastName");
	                	doctorBySpe.add(fname+" "+lname+" is specialized in - "+speciality);
	                }
	            } catch (SQLException e) {System.out.println(e);}
	            return doctorBySpe;
	        }

    	/*Query 13*/    	
    	public Vector<String> selectPatientByDate(String day){
    		Vector<String> patientByDate= new Vector<String>();
        	try {
        		Statement stmt = connectionDBusers.createStatement();            
                ResultSet rs = stmt.executeQuery("SELECT FirstName, LastName FROM Patient WHERE DayIn= '"+day+"'");
                while (rs.next()){
                	String fname = rs.getString("FirstName");
                	String lname = rs.getString("LastName");
                   	patientByDate.add(fname+" "+lname+" entred in- "+day);
                }
            } catch (SQLException e) {System.out.println(e);}
            return patientByDate;
        }

    	/*Query 14*/	
    	public Vector<String> selectDirectorInfo(String director){
    		Vector<String> directorInfo= new Vector<String>();
        	try {
        		Statement stmt = connectionDBusers.createStatement();            
                ResultSet rs = stmt.executeQuery("SELECT * FROM Department WHERE Director ='"+director+"'");
                while (rs.next()){
                	String fname = rs.getString("FirstName");
                	String lname = rs.getString("LastName");
                	directorInfo.add(fname+" "+lname);
                }
            } catch (SQLException e) {System.out.println(e);}
            return directorInfo;
        }
    	
    	/*Query 15*/   	
	    public Vector<String> selectNurseDetails(){
	    		Vector<String> nurseDetails= new Vector<String>();
	        	try {
	        		Statement stmt = connectionDBusers.createStatement();            
	                ResultSet rs = stmt.executeQuery("SELECT SSN, FirstName, LastName FROM Nurse");
	                while (rs.next()){
	                	String fname = rs.getString("FirstName");
	                	String lname = rs.getString("LastName");
	    				String ssn = rs.getString("SSN");
	                   	nurseDetails.add(fname+" "+lname+"and his/her SSN is" +ssn);
	                }
	            } catch (SQLException e) {System.out.println(e);}
	            return nurseDetails;
	        }	
    
    /* Query 19*/ 	
    public void selectDeletePatient(String ssn){
        	try {
        		Statement stmt = connectionDBusers.createStatement();            
                stmt.executeQuery("DELETE FROM Patient WHERE SSN ='"+ssn+"'");
            } catch (SQLException e) {System.out.println(e);}
        }	
    
    /* Query 20*/
    public Vector<String> deleteClosedAccount(){
		Vector<String> closedAccount = new Vector<String>();
    	try {
    		Statement stmt = connectionDBusers.createStatement();            
            ResultSet rs = stmt.executeQuery("DELETE FROM Account WHERE DayClose IS NOT null");
            while (rs.next()){            	
               	closedAccount.add("All closed accoutn is deleted from the database.");
            }
        } catch (SQLException e) {System.out.println(e);}
        return closedAccount;
    }
    
    /*Query 21*/    
    public void insertNewDepartment(String nam, String dir){
    	try {
    		Statement stmt = connectionDBusers.createStatement();            
            		stmt.execute("INSERT INTO Department values ('"+nam+"', '"+dir+"') ");
        } catch (SQLException e) {System.out.println(e);}
    }
  
    /*Query 22*/    
    public Vector<String> updateNewDirector(String director, String dep){
		Vector<String> newDirector = new Vector<String>();
    	try {
    		Statement stmt = connectionDBusers.createStatement();            
            ResultSet rs = stmt.executeQuery("UPDATE Department SET Director = '"+director+"' WHERE Name = '"+dep + "'");
            while (rs.next()){
               	newDirector.add(director+" is the new director of "+dep+ " department");
            }
        } catch (SQLException e) {System.out.println(e);}
        return newDirector;
    }
    
    /*Query 23*/
    public Vector<String> updateNumberNurse(String floor , int num){
		Vector<String> numberNurse = new Vector<String>();
    	try {
    		Statement stmt = connectionDBusers.createStatement();            
            ResultSet rs = stmt.executeQuery("UPDATE Floor SET NumbernNurse = '"+num+"' WHERE FloorNumber = '"+floor + "'");
            while (rs.next()){            	
               	numberNurse.add("Now there are "+num+" nurses in Floor - "+floor);
            }
        } catch (SQLException e) {System.out.println(e);}
        return numberNurse;
    }
    
    /*Query 24*/
    public void insertNewFloor(String floorNumber, int rooms, int nurses){
    	try {
    		Statement stmt = connectionDBusers.createStatement();            
            		stmt.execute("INSERT INTO Floor values ('"+floorNumber+"', '"+rooms+"', '"+nurses+"')" );
        } catch (SQLException e) {System.out.println(e);}
    }
    
    /*Query 25*/
    public void insertNewDoctor(String dssn, String dfn, String dln, String dsex,String dadd, String ddate, String dspe, String ddep){
    	try {
    		Statement stmt = connectionDBusers.createStatement();            
            		stmt.execute(" INSERT INTO Doctor values ('"+dssn+"', '"+dfn+"', '"+dln+"', '"+dsex+"', '"+dadd+"', '"+ddate+"', '"+dspe+"', '"+ddep+"') " );
        } catch (SQLException e) {System.out.println(e);}
    }
    
    /*Query 26*/
	 public void insertNewNurse(String nssn, String nfn, String nln, String nsex,String nadd, String ndate, String nfloor){
	    	try {
	    		Statement stmt = connectionDBusers.createStatement();            
	            		stmt.execute(" INSERT INTO Nurse" +
	            							" values ('"+nssn+"', '"+nfn+"', '"+nln+"', '"+nsex+"', '"+nadd+"', '"+ndate+"', '"+nfloor+"') " );
	        } catch (SQLException e) {System.out.println(e);}
	    }
}