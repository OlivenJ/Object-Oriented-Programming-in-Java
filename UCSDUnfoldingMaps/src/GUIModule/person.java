package GUIModule;

public class person {

	private String name;
	
	public person(String n) {
		this.name = n;
		System.out.println("#1");
		
		
	}
	


public class Student extends person{
	
	public Student() {
	this("Student");
	System.out.println("#2");
	
	}

public Student(String n) {
	super(n);
	System.out.println("#3");
		}

	}


public static void main(String[] args) {
	
	Student s = new Student();
	
	
}

}