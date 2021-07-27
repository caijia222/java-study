package jdbc;

public class Student {

	private long id;
	private String name;
	private int gender;
	private int grade;
	private int score;
	
	public Student(long id, String name, int gender, int grade, int score) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.grade = grade;
		this.score = score;
	}
	
	
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", gender=" + gender + ", grade=" + grade + ", score=" + score
				+ "]";
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}
