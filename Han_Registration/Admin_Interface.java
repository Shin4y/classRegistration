
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public interface Admin_Interface {
	
	public void addCourse(Course newCourse);
	
	
	public Course courseSearch(String courseId);
	
	public static Student studentSearch(String username) {
		return null;
	}
	
	public static void updateData() {
	}
	
	public void EditCourse() throws IOException;
	
	public void createCourse() throws IOException;
	
	public void deleteCourse() throws IOException;
	
	public void courseInfo() throws IOException;
	
	public void viewAllCourses();
	
	public void viewFullCourses();
	
	public void updateFullList();
	
	public void peekStudent() throws IOException; //Look at all registered courses for Student
	
	public void sortCourses();
	
	public void peekCourse() throws IOException;
	
	public void serialize();
	
	public void deSerialize() throws ClassNotFoundException;
	

}
