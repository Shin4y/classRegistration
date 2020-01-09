
import java.io.IOException;
import java.util.ArrayList;

public interface Student_Interface {
	
	public static ArrayList<Student> getStudentList(){
		return null;
	}
	
	public void viewAllCourses();
	
	public void viewEmptyCourses();
	
	public void register() throws IOException;
	
	public void withdraw() throws IOException;
	
	public void viewCoursesRegistered();
	
	
}
