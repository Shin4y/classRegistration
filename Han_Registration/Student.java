
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;

public class Student extends User implements Student_Interface, Serializable{ //the general student class
	
	private static ArrayList<Student> allStudent = new ArrayList();//list of every student
	private ArrayList<Course> enrolledCourses;//Keeping track of courses that the student is registered in
	
	
	public Student(String username, String password, String firstName, String lastName) { //the actual constructor
		super(username, password, firstName, lastName);
		this.enrolledCourses = new ArrayList();
		
		// TODO Auto-generated constructor stub
	}
	
	public Student(){ // just a bs constructor
		this.firstName = "";
	}
	
	public static ArrayList<Student> getStudentList(){
		return allStudent;
	}
	
	public ArrayList<Course> getEnrolledCourses(){
		return enrolledCourses;
	}

	@Override
	public void viewCoursesRegistered() {//prints courses from enrolled courses list
		// TODO Auto-generated method stub 
		if(this.enrolledCourses.size() == 0){
			System.out.println("No classes registered");
		}
		else{
			for(int i = 0; i < this.getEnrolledCourses().size(); i++){
				System.out.println(this.getEnrolledCourses().get(i).getCourseName());
			}
		}
		
	}
	
	private Course courseSearch(String courseName){ //searches for course based on course name
		for(int i = 0; i < Course.getCourseList().size(); i++){
			if(Course.getCourseList().get(i).getCourseName().equals(courseName)){
				return Course.getCourseList().get(i);
			}
		}
		return null;
	}

	@Override
	public void register() throws IOException {//after input, registers student in class specified
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Course Name: ");
		String courseName = in.readLine();
		System.out.println("Section #: ");
		String sectNumber = in.readLine();
		System.out.println("First name: ");
		String firstName = in.readLine();
		System.out.println("Last name: ");
		String lastName = in.readLine();
		Course course = courseSearch(courseName);
		course.addStudent(firstName, lastName);
		this.enrolledCourses.add(course);
		
		
	}

	@Override
	public void viewEmptyCourses() {
		// TODO Auto-generated method stub
		for(int i = 0; i < Course.getCourseList().size(); i++){
			if(Course.getCourseList().get(i).getCurrStudents() < Course.getCourseList().get(i).getMaxStudents()){
				System.out.println(Course.getCourseList().get(i).getCourseName());
			}
		}
	}

	@Override
	public void withdraw() throws IOException { //withdraws student from entered course
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Course Name: ");
		String courseName = in.readLine();
		System.out.println("First name: ");
		String firstName = in.readLine();
		System.out.println("Last name: ");
		String lastName = in.readLine();
		Course course = courseSearch(courseName);
		
		course.remStudent(firstName, lastName);
		this.enrolledCourses.remove(courseSearch(courseName));
	}


	@Override
	public Course courseSearch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void viewAllCourses() {
		for(int i = 0; i < Course.getCourseList().size(); i++){
			System.out.println(Course.getCourseList().get(i).getCourseName());
		}
		// TODO Auto-generated method stub
		
	}
	
	public static void updateStudentList(ArrayList<Student> data){
		allStudent = data;
	}
	
	public static void studentLogin(Admin admin) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int failed = 0;
		String username;
		String password;
		String check;
		while(failed <= 3){ //Breaks if failed 3+ times
			System.out.println("Enter username");
			username = in.readLine();
			check = Admin.studentSearch(username).getPassword();
			System.out.println("Enter password");
			password = in.readLine();
			if(!password.equals(check)){
				failed++;
			}
			else{
				studentMenu(Admin.studentSearch(username), admin);
			}
		}
		return;
		
	}
	
	public static void studentMenu(Student student, Admin admin) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		boolean end = false;
		while(!end){	
			System.out.format("(1) View all courses\n(2) View courses not full\n(3) Register in course \n(4) Widthdraw from course\n"
				+ "(5) View all courses currently registered\n(6) exit\n");
			String answer = in.readLine();
			int choice = Integer.parseInt(answer);
			switch(choice){
			case(1): student.viewAllCourses();
			break;
			case(2): student.viewEmptyCourses();
			break;
			case(3): student.register();
			break;
			case(4): student.withdraw();
			break;
			case(5): student.viewCoursesRegistered();
			break;
			case(6): admin.updateData();
			admin.serialize();
			System.exit(0);
			break;
			}
		
		}
	}
	
}


