
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Admin extends User implements Admin_Interface, Serializable{
	
	private ArrayList<Student>studentList = new ArrayList(); //nonstatic placeholders for courselist
	private ArrayList<Course>courseList = new ArrayList();  //and studentList when serializing
	private static String username = "Admin";
	private static String password = "Admin001";

	public Admin(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
		// TODO Auto-generated constructor stub
	}
	
	public void addCourse(Course newCourse){
		Course.getCourseList().add(newCourse);
	} 
	
	public Course courseSearch(String courseId){ //searching for course based on course ID 
		for(int i = 0; i < Course.getCourseList().size(); i++){
			if(Course.getCourseList().get(i).getCourseId().equals(courseId)){
				return Course.getCourseList().get(i);
			}
		}
		return null;
	}
	
	
	/*public void delCourse(String courseName){
		for(int i = 0; i < Course.size(); i++){
			if(((Course)master.get(i)).getCourseName() == courseName){
				master.remove(i);
				break;
			}
		}
	}*/
	
	
	
	/*private void editMaxStudents(int size, Course course){
		course.setMaxStudents(size);
	}
	
	private void editCourseInstructor(String name, Course course){
		course.setCourseInstructor(name);
		
	}
	
	private void editSectNumber(int number, Course course){
		course.setSectNumber(number);
		
	}
	
	private void editCourseLoc(String location, Course course){
		course.setCourseLoc(location);
	}*/
	
	public static Student studentSearch(String username){ //searching for student based on username
		for(int i = 0; i < Student.getStudentList().size(); i++){
			if(Student.getStudentList().get(i).getUsername().equals(username)){
				return (Student)Student.getStudentList().get(i);
			}
		}
		return null;
	}
	
	public void updateData(){ //taking copies of the student list and courseList before 
		this.studentList = Student.getStudentList(); //serializing
		this.courseList = Course.getCourseList();
	}
	

	public void EditCourse() throws IOException{ //the menu for editing courses
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		
		Course course = null;
		
		while(course == null){
			System.out.println("What is the courseId of the class to be edited?"); //loop until class id is correctly entered 
			String courseId = in.readLine();
			course = courseSearch(courseId);	
		}
		System.out.println("1: addStudents, 2: editMaxStudents, 3: editCourseInstructor, 4: editSectNumber, 5: editCourseLoc");
		String answer = in.readLine();
		int ans = Integer.parseInt(answer);

		switch (ans){
		
		case(1):
			System.out.println("Enter username of Student:");
			answer = in.readLine();
			if(Admin.studentSearch(answer) != null){
				course.addStudent(Admin.studentSearch(answer).getFirstName(), Admin.studentSearch(answer).getLastName());
				Admin.studentSearch(answer).getEnrolledCourses().add(course);
			}
			break;
		
		case(2):
			System.out.println("Enter integer to change to max");
			answer = in.readLine();
			course.setMaxStudents(Integer.parseInt(answer));
			break;
		
		case(3):
			System.out.println("Enter new course instructor");
			answer = in.readLine();
			course.setCourseInstructor(answer);
			break;
			
		case(4):
			System.out.println("Enter new section number");
			answer = in.readLine();
			course.setSectNumber(Integer.parseInt(answer));
			break;
			
		case(5):
			System.out.println("Enter new course location");
			answer = in.readLine();
			course.setCourseLoc(answer);
			break;
		
		}
	}
	
	public void createCourse() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter course name:");
		String courseName = in.readLine();
		System.out.println("Enter course ID");
		String courseId = in.readLine();
		System.out.println("Enter max students");
		String maxStudents = in.readLine();
		System.out.println("Enter course Instructor");
		String courseInstructor = in.readLine();
		System.out.println("Enter section number");
		String sectNumber = in.readLine();
		System.out.println("Enter course Location");
		String location = in.readLine();
		
		int currStudents = 0;
		
		Course newCourse = new Course(courseName, courseId, Integer.parseInt(maxStudents), currStudents, courseInstructor, Integer.parseInt(sectNumber), location);
		Course.getCourseList().add(newCourse);
	}
	
	public void deleteCourse() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter course ID to be deleted:");
		String answer = in.readLine();
		if(courseSearch(answer) != null){
			Course course = courseSearch(answer);
			int index = Course.getCourseList().indexOf(course);
			Course.getCourseList().remove(index);
		}
	}
	
	public void courseInfo() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter course ID desired: ");
		String answer = in.readLine();
		answer.replaceAll("\\s+","");
		if(courseSearch(answer) != null){
			Course course = courseSearch(answer);
			System.out.format("Course name: %s Max students: %d Current students: %d Course Instructor %s Section number: %d Class location: %s%n",
					course.getCourseName(), course.getMaxStudents(), course.getCurrStudents(), course.getCourseInstructor(), course.getSectNumber(), course.getCourseLoc());
			System.out.println("Roster:");
			for(int i = 0; i < course.getRoster().size(); i++){
				System.out.format("%s %n", course.getRoster().get(i));
			}
		}
		else{
			System.out.println("Course does not exist.");
		}
	}
	
	public void viewAllCourses(){
		for(int i = 0; i < Course.getCourseList().size(); i++){
			System.out.format("%s%n", Course.getCourseList().get(i).getCourseName());
		}
	}
	
	public void register() throws IOException{//registering a student into the course system
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("First Name: ");
		String firstName = in.readLine();
		System.out.println("Last Name: ");
		String lastName = in.readLine();
		System.out.println("username: ");
		String username = in.readLine();
		System.out.println("password: ");
		String password = in.readLine();
		
		boolean dupe = false;
		Student temp = new Student(username, password, firstName, lastName);//checking for dupes
		for(int index = 0; index < Student.getStudentList().size(); index++){
			Student compare = Student.getStudentList().get(index);
			if(compare.getUsername().equals(temp.getUsername())){
				dupe = true;
			}
			
			if(compare.getPassword().equals(temp.getPassword())){
				dupe = true;
			}
			
		}
		
		if(!dupe){
			System.out.println("Student added");
			Student.getStudentList().add(temp);
		}
		else{
			System.out.println("Duplicate found in system");
		}
		
	}
	
	public void peekCourse() throws IOException{ //printing the roster of a course 
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Course Id: ");
		String answer = in.readLine();
		Course course = this.courseSearch(answer);
		
		if(course == null){
			System.out.println("No course found");
		}
		else{
			for(int i = 0; i < course.getRoster().size(); i++){
				System.out.format("%s %n", course.getRoster().get(i));
			}
		}
	}
	
	public void peekStudent() throws IOException{ //printing courses registered for student 
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("First Name:");
		String firstName = in.readLine();
		System.out.println("Last Name:");
		String lastName = in.readLine();
		
		Student target = null;
		
		for(int i = 0; i < Student.getStudentList().size(); i++){
			if(Student.getStudentList().get(i).getFirstName().equals(firstName) && Student.getStudentList().get(i).getLastName().equals(lastName)){
				target = Student.getStudentList().get(i);
			}
		}
		
		if(target == null){
			System.out.println("This student is not enrolled in any courses");
		}
		else{
			for(int i = 0; i < target.getEnrolledCourses().size(); i++){
				System.out.format("%s%n", target.getEnrolledCourses().get(i).getCourseName());
			}
		}
	}
	
	public void updateFullList(){ //updating the list of full courses to a text file
		String fileName = "text.txt";
		Scanner scan = new Scanner(System.in);
		boolean end = false;
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try{
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for(int index = 0; index < Course.getCourseList().size(); index++){
				if(Course.getCourseList().get(index).getCurrStudents() == Course.getCourseList().get(index).getMaxStudents()){
					bufferedWriter.write(Course.getCourseList().get(index).getCourseName());
					System.out.println("written");
				}
			}
			/*while(!end){
				System.out.println("Enter class that is full: ");
				String course = in.readLine();
				
				bufferedWriter.write(course);
				bufferedWriter.newLine();
				System.out.println("Enter more? (y/n: ");
				course = in.readLine();
				if(course.equals("n")){
					end = true;
				}
			}*/

			
//Always close writer
			bufferedWriter.close();
		}

		//Always close files

		catch (IOException exk) {
			System.out.println( "Error writing file '" + fileName + "'");
			exk.printStackTrace();
		}
	}
	
	public void viewFullCourses(){ //printing out the full coursers
		
		boolean nothing = true;
		for(int index = 0; index < Course.getCourseList().size(); index++){
			if(Course.getCourseList().get(index).getCurrStudents() == Course.getCourseList().get(index).getMaxStudents()){
				System.out.println(Course.getCourseList().get(index).getCourseName());
				nothing = false;
			}
		}
		
		if(nothing == true){
			System.out.println("No classes are full.");
		}
	}
	
	public void sortCourses(){ //sorting courses
		Collections.sort(Course.getCourseList(), new sortByStudents());
	}

	
	public Course courseSearch() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void serialize(){
		try {
			//FileOutput Stream writes data to a file
			FileOutputStream fos = new FileOutputStream("File.ser");
			
			//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			//System.out.println(this.studentList.get(0).getFirstName());
			System.out.println("nothing");
			//Writes the specific object to the OOS
			oos.writeObject(this);
			
			//Close both streams
			oos.close();
			fos.close();
			System.out.println("Serialization complete");
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void deSerialize() throws ClassNotFoundException{
		try{
			  //FileInputSystem recieves bytes from a file
		      FileInputStream fis = new FileInputStream("File.ser");
		      
		      //ObjectInputStream does the deserialization-- it reconstructs the data into an object
		      ObjectInputStream ois = new ObjectInputStream(fis);
		      
		      //Cast as Employee. readObject will take the object from ObjectInputStream
		      Admin data = (Admin)ois.readObject();
		      ois.close();
		      fis.close();
		      
		     
		      //System.out.println(data.studentList.get(0).getFirstName());
		      this.courseList = data.courseList;
		      this.studentList = data.studentList;
		      Course.updateCourseList(this.courseList);
		      Student.updateStudentList(this.studentList);
		    }
		    catch(IOException ioe) {
		       ioe.printStackTrace();
		       return;
		    }
		
		}
	
	public static void adminLogin(Admin admin) throws IOException{ //the admin login menu
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int failed = 0;
		String username;
		String password;
		String check;
		while(failed <= 3){
			System.out.println("Enter username");
			username = in.readLine();
			check = Admin.password;
			System.out.println("Enter password");
			password = in.readLine();
			if(!password.equals(check)){
				failed++;
			}
			else{
				adminMenu(admin);
			}
		}
		return;
		
	}
	
	public static void adminMenu(Admin admin) throws IOException{ //the main admin menu
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));//where you can do things
		boolean end = false;
		while(!end){
			System.out.println("(1) Course Management, (2) Report");
			String answer = in.readLine();
			if(answer.equals("1")){
				System.out.format("(1) Create a new Course\n(2) Delete a course\n(3) Edit a course\n(4) Display information for given course\n"
					+ "(5) Register a student\n(6) exit\n");
				answer = in.readLine();
				int number = Integer.parseInt(answer);
				switch(number){
					case(1): admin.createCourse();
					break;
					case(2): admin.deleteCourse();
					break;
					case(3): admin.EditCourse();
					break;
					case(4): admin.courseInfo();
					break;
					case(5): admin.register();
					break;
					case(6): admin.updateData();
					admin.serialize();
					System.exit(0);
					break;
				}
			}
			else if(answer.equals("2")){
				System.out.format("(1) View all courses\n(2) View all full courses\n(3) Write to file list of full courses\n"
						+ "(4) View roster of course\n(5) View courses student is registered in\n(6) Sort courses\n(7) exit\n");
				answer = in.readLine();
				int number = Integer.parseInt(answer);
				switch(number){
				case(1): admin.viewAllCourses();
				break;
				case(2): admin.viewFullCourses();
				break;
				case(3): admin.updateFullList();
				break;
				case(4): admin.peekCourse();
				break;
				case(5): admin.peekStudent();
				break;
				case(6): admin.sortCourses();
				break;
				case(7): admin.updateData();
				admin.serialize(); 
				System.exit(0);
				case(8): System.out.println(Student.getStudentList().get(0).getFirstName());
				break;
				
				}
		
			}
		}
	}
}


class sortByStudents implements Comparator{

	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return (int)((Course) o1).getCurrStudents() - (int)((Course) o2).getCurrStudents();
	}
}
