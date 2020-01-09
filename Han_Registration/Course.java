
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable{
	private String courseName;
	private String courseId;
	private int maxStudents;
	private int currStudents;
	private ArrayList<String> roster;
	private String courseInstructor;
	private int sectNumber;
	private String courseLoc;
	
	private static ArrayList CourseList = new ArrayList();
	private static ArrayList fullList = new ArrayList();
	
	
	
	public Course(String courseName, String courseId, int maxStudents, int currStudents, String courseInstructor, 
			int sectNumber, String courseLoc){
		this.courseName = courseName;
		this.courseId = courseId;
		this.maxStudents = maxStudents;
		this.currStudents = currStudents;
		this.courseInstructor = courseInstructor;
		this.sectNumber = sectNumber;
		this.courseLoc = courseLoc;
		this.roster = new ArrayList<String>();
		
	}
	
	public static void fillCourseList_csv() throws IOException{ //first time using program, reads csv for courses
		
		String csvFile = "MyUniversityCourses.csv";
		String csvSplitBy = ",";
		String line = "";
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		line = br.readLine();

		 while ((line = br.readLine()) != null) {
				 

	         // use comma as separator
			 String[] data = line.split(csvSplitBy);
				 
			 Course placeHolder = new Course(data[0], data[1], 
				 Integer.parseInt(data[2]), Integer.parseInt(data[3]),data[5],
				 Integer.parseInt(data[6]), data[7]); //Skipping index 4 because that is the roster value but it is NULL.
				 
			 //System.out.println(placeHolder.getCourseName());
				 
			 CourseList.add(placeHolder);

		}
		
	}
	

	public String getCourseName() {
		return courseName;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public int getMaxStudents() {
		return maxStudents;
	}

	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}

	public int getCurrStudents() {
		return currStudents;
	}

	public  void setCurrStudents(int currStudents) {
		this.currStudents = currStudents;
	}

	public String getCourseInstructor() {
		return courseInstructor;
	}

	public void setCourseInstructor(String courseInstructor) {
		this.courseInstructor = courseInstructor;
	}

	public int getSectNumber() {
		return sectNumber;
	}

	public void setSectNumber(int sectNumber) {
		this.sectNumber = sectNumber;
	}

	public String getCourseLoc() {
		return courseLoc;
	}

	public void setCourseLoc(String courseLoc) {
		this.courseLoc = courseLoc;
	}
	
	public ArrayList<String> getRoster(){
		return this.roster;
	}
	public void addStudent(String firstName, String lastName){ //checks if class is not full, and then adds student to roster
		if(this.currStudents >= this.maxStudents){
			System.out.println("Too many students in class already.");
		}
		
		else{
			this.roster.add(firstName + " " + lastName);
			this.currStudents += 1;
			
		}
	}
	
	public void remStudent(String firstName, String lastName){ //checks if class is not empty, 
		if(this.currStudents <= 0){							   //and removes student
			System.out.println("There are no students currently in this class.");
		}
		boolean succeed = false;
		for(int i = 0; i < this.currStudents; i++){
			if(this.roster.get(i).equals(firstName + " " + lastName)){
				this.roster.remove(i);
				succeed = true;
				break;
			}
		}
		
		if(succeed == false){
			System.out.println("No student with that username was found");
		}
		
		
	}
	
	public static ArrayList<Course> getCourseList(){
		return CourseList;
	}
	
	public static ArrayList<Course> getFullList(){
		return fullList;
	}
	
	public static void updateCourseList(ArrayList<Course> data){//after deserializing, 
		CourseList = data;										//populates courseList from .ser
	}
}
