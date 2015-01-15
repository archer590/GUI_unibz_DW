package connection;

public class Internship {
	
	String month;
	int lag_month;
	int lead_month;
	int students;
	
	String country;
	int year;
	double salary;
	int ranking;	

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public Internship(String month, int lag_month, int lead_month, int students, String country, int year, double salary, int ranking) {
		super();
		this.month = month;
		this.lag_month = lag_month;
		this.lead_month = lead_month;
		this.students = students;
		this.country = country;
		this.year = year;
		this.salary = salary;
		this.ranking = ranking;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getLag_month() {
		return lag_month;
	}

	public void setLag_month(int lag_month) {
		this.lag_month = lag_month;
	}

	public int getLead_month() {
		return lead_month;
	}

	public void setLead_month(int lead_month) {
		this.lead_month = lead_month;
	}

	public int getStudents() {
		return students;
	}

	public void setStudents(int students) {
		this.students = students;
	}


}
