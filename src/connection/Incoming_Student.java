package connection;

public class Incoming_Student {
	private String faculty = null;
	private String study_plan = null;
	private String curriculum_name = null;
	private int count = 0;
	
	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getStudy_plan() {
		return study_plan;
	}

	public void setStudy_plan(String study_plan) {
		this.study_plan = study_plan;
	}

	public String getCurriculum_name() {
		return curriculum_name;
	}

	public void setCurriculum_name(String curriculum_name) {
		this.curriculum_name = curriculum_name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Incoming_Student(String f, String sp, String cn, int c){
		faculty=f;
		study_plan=sp;
		curriculum_name=cn;
		count=c;
	}
	

}
