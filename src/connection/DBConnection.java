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
    public Vector<Internship> internships = new Vector<Internship>();
    public Vector<Graduation> graduations = new Vector<Graduation>();
    
    
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
        String host = "localhost:";
		String port = "5432";
		try {
			Class.forName("org.postgresql.Driver");
//			String connectionUrl1 = "jdbc:postgresql://" + host + port + "/" + "admt2014_users";
//			connectionDBusers = DriverManager.getConnection(connectionUrl1, "admt2014", "admt");
//			
//			Statement stmt = connectionDBusers.createStatement();            
//            ResultSet rs = stmt.executeQuery("SELECT COUNT(user_key) AS ussdw FROM user_dw WHERE username ='"+username+"' AND password ='"+password+"'");
//
//            int numberUser = 0;
//            while (rs.next()){
//            	numberUser = Integer.parseInt(rs.getString("ussdw"));           	
//            }
//
//			if (numberUser==1){
//				
//				Statement stmt2 = connectionDBusers.createStatement();            
//	            ResultSet rs2 = stmt2.executeQuery("SELECT name FROM user_dw WHERE username ='"+username+"' AND password ='"+password+"'");
//	            while (rs2.next()){
//	            	setName(rs2.getString("name"));           	
//	            }
//	            closeDBusersConnection();
	            String connectionUrl = "jdbc:postgresql://" + host + port + "/" + "postgres";
				conn = DriverManager.getConnection(connectionUrl, "postgres", "admin");
				
//				return true;
//			}
//			else 
//				setLogin(false);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return true;
    }
    
    // disconnect from the database
    public void closeDBusersConnection() throws SQLException{
    	conn.close();
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
    
    public Vector<Internship> windowing(){
		if(!internships.isEmpty())
			internships.clear();
    	try {
    		Statement stmt = conn.createStatement();            
            ResultSet rs = stmt.executeQuery("SELECT dd.month, count(inf.student_key) as total, lag(count(inf.student_key),1) OVER (order by dd.month) as lag_month, lead(count(inf.student_key),1) OVER (order by dd.month) as lead_month FROM admt2014_unibzdw.internship_fact inf join admt2014_unibzdw.date_dimension dd on inf.start_date_key=dd.date_key where year='2012' group by dd.month;");
            
            while (rs.next()){
            	String month = nameMonth(rs.getInt("month"));
            	int students = rs.getInt("total");
				int lag_month = rs.getInt("lag_month");	
				int lead_month = rs.getInt("lead_month");
				internships.add(new Internship(month, lag_month, lead_month, students, null, 0, 0, 0));
            }
        } catch (SQLException e) {System.out.println(internships.size());System.out.println(e.getMessage()+e.getLocalizedMessage());}
        return internships;
    }
    
    
    public static String nameMonth(int n){
    	switch(n){
    	case 1:
    		return "January";
    	case 2:
    		return "February";
    	case 3:
    		return "March";
    	case 4:
    		return "April";
    	case 5:
    		return "May";
    	case 6:
    		return "June";
    	case 7:
    		return "July";
    	case 8:
    		return "August";
    	case 9:
    		return "September";
    	case 10:
    		return "October";
    	case 11:
    		return "November";
    	case 12:
    		return "December";
    	}
    	return null;
    }
    
    
    
    public Vector<Internship> ranking(){
		if(!internships.isEmpty())
			internships.clear();
    	try {
    		Statement stmt = conn.createStatement();            
            ResultSet rs = stmt.executeQuery("SELECT cT.country as Country, dT.year as year, AVG(infoT.salary) as Average_Salary, RANK() OVER (PARTITION BY year ORDER BY AVG(infoT.salary) DESC) as Ranking FROM admt2014_unibzdw.company_dimension cT JOIN admt2014_unibzdw.internship_fact iT ON iT.company_key =cT.company_key JOIN admt2014_unibzdw.date_dimension dT on iT.start_date_key= dT.date_key JOIN admt2014_unibzdw.internship_info_dimension infoT ON infoT.info_key = iT.info_key WHERE cT.country IN ('Indonesia', 'China', 'Sweden', 'Russia') AND dT.year IN (2008, 2009, 2010, 2011, 2012, 2013, 2014) GROUP BY cT.country, dT.year;");
            
            while (rs.next()){
            	String country = rs.getString("Country");
            	int year = rs.getInt("year");
				double salary = rs.getDouble("Average_Salary");	
				int ranking = rs.getInt("Ranking");
//				System.out.println(country+" "+year+" "+salary+" "+ranking);
				internships.add(new Internship(null, 0, 0, 0, country, year, salary, ranking));
            }
        } catch (SQLException e) {System.out.println(internships.size());System.out.println(e.getMessage()+e.getLocalizedMessage());}
        return internships;
    }
    
    public Vector<Graduation> grad_year_sp(){
		if(!graduations.isEmpty())
			graduations.clear();
    	try {
    		Statement stmt = conn.createStatement();            
            ResultSet rs = stmt.executeQuery("SELECT sd.student_key, sd.year, sd.study_plan FROM admt2014_unibzdw.degreed_year_study_plan sd WHERE sd.status='Degreed' GROUP BY sd.student_key, sd.year, sd.study_plan ORDER BY sd.year, sd.study_plan;");
            
            while (rs.next()){
            	int key_one = rs.getInt("student_key");
            	int key_two = rs.getInt("year");
				String name = rs.getString("study_plan");	
				graduations.add(new Graduation(key_one, key_two, name, null));
            }
        } catch (SQLException e) {System.out.println(graduations.size());System.out.println(e.getMessage()+e.getLocalizedMessage());}
        return graduations;
    }
    
    public Vector<Graduation> grad_year_fac(){
		if(!graduations.isEmpty())
			graduations.clear();
    	try {
    		Statement stmt = conn.createStatement();            
            ResultSet rs = stmt.executeQuery("SELECT sd.student_key, sd.year,sd.faculty FROM admt2014_unibzdw.degreed_year_faculty sd WHERE sd.status='Degreed' GROUP BY sd.student_key, sd.year,sd.faculty ORDER BY sd.year;");
            
            while (rs.next()){
            	int key_one = rs.getInt("student_key");
            	int key_two = rs.getInt("year");
				String name = rs.getString("faculty");	
				graduations.add(new Graduation(key_one, key_two, name, null));
            }
        } catch (SQLException e) {System.out.println(graduations.size());System.out.println(e.getMessage()+e.getLocalizedMessage());}
        return graduations;
    }
    
    public Vector<Graduation> grad_fac_sup(){
		if(!graduations.isEmpty())
			graduations.clear();
    	try {
    		Statement stmt = conn.createStatement();            
            ResultSet rs = stmt.executeQuery("SELECT sd.student_key, sd.faculty, sd.surname FROM admt2014_unibzdw.degreed_faculty_supervisor sd WHERE sd.status='Degreed' GROUP BY sd.student_key, sd.faculty, sd.surname ORDER BY sd.faculty;");
            
            while (rs.next()){
            	int key_one = rs.getInt("student_key");
            	String name_one = rs.getString("faculty");
				String name = rs.getString("surname");	
				graduations.add(new Graduation(key_one, 0, name, name_one));
            }
        } catch (SQLException e) {System.out.println(graduations.size());System.out.println(e.getMessage()+e.getLocalizedMessage());}
        return graduations;
    }
    
}