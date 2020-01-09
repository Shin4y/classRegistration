
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Admin admin = new Admin("first", "last", "Admin", "Admin001"); //create the one and only admin
		Login login = new Login();
		
		login.loginScreen(admin);
		
		
		
		
	}

}
