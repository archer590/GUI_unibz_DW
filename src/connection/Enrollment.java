package connection;

public class Enrollment {
	
	private int year = 0;
	private int students = 0;
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getStudents() {
		return students;
	}

	public void setStudents(int students) {
		this.students = students;
	}
	
	public Enrollment(int year, int students){
		this.year = year;
		this.students = students;
	}

}
