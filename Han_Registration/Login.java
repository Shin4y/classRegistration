 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Login { //class used to instantiate the first login screen
	
	public Login(){
	}
	
	public void loginScreen(Admin admin) throws IOException, ClassNotFoundException{ //first login screen, directs user to Admin or student login
		System.out.println("First time logging in? y/n: ");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String answer = in.readLine();
		if(answer.equals("y")){
			Course.fillCourseList_csv();
		}
		else if(answer.equals("n")){
			admin.deSerialize();
		}
		System.out.println("(S) Student or (A) Admin?");
		answer = in.readLine();
		if(answer.equals("S")){
			Student.studentLogin(admin);
		}
		else if(answer.equals("A")){
			Admin.adminLogin(admin);
		}
	}
}
		
	
