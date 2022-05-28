package sis;

import java.util.ArrayList;

public class Course {

	private String name;
	private int credits;
	private String courseCode;
	private ArrayList<Grade> stdGrades;

	Course() {
		this.name = this.courseCode = "";
		this.credits = -1;
		this.stdGrades = new ArrayList<Grade>();
	}

	Course(String name, String courseCode, int credits, ArrayList<Grade> stdGrades) {
		this.name = name;
		this.courseCode = courseCode;
		this.credits = credits;

		if (stdGrades != null) {
			this.stdGrades = stdGrades;
		}
		else this.stdGrades = new ArrayList<Grade>();

	}
	Course(String name, String courseCode, int credits, Grade stdGrades) {
		this.name = name;
		this.courseCode = courseCode;
		this.credits = credits;
		this.stdGrades = new ArrayList<Grade>();
		if (stdGrades != null) {
			this.stdGrades.add(stdGrades);
		}
		else this.stdGrades = new ArrayList<Grade>();
	}
	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredits() {
		return this.credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String coursecode) {
		this.courseCode = coursecode;
	}


	public ArrayList<Grade> getStdGrades() {
		return stdGrades;
	}

	public void setStdGrades(ArrayList<Grade> stdGrades) {
		int i = 0;
		this.stdGrades = new ArrayList<Grade>();
		while (i < stdGrades.size()) {
			this.stdGrades.add(stdGrades.get(i));
			++i;
		}
	}


}
