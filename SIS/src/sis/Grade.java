package sis;

public class Grade {
	private char letter;
	private int grade;
	
	
	public Grade() {
		letter = '\0';
		grade = -1;
	}
	public Grade(int grade) {
		
		this.setGrade(grade);
		this.setLetter(letterCalc(grade));
	}
	public Grade(int grade, char letter) {
		this.grade = grade;
		this.letter = letter;
	}
	
	private char letterCalc(int e) {
		
		if (e >= 90) {
			return 'A';
		}
		else if (e >= 80) {
			return 'B';
		}
		else if (e >= 70) {
			return 'C';
		}
		else if (e >= 60)
		{
			return 'D';
		}
		else return 'F';
	}

	public char getLetter() {
		return letter;
	}
	public void setLetter(char letter) {
		this.letter = letter;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
		this.letter = letterCalc(grade);
	}
	
	@Override
	public String toString() {
		return "LetterGrade=" + this.letter + ", NumericValue=" + this.grade + "\n";
	}
}
