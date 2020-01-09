
import java.io.IOException;
import java.io.Serializable;

public abstract class User implements Serializable{
	protected String username;
	protected String password;
	protected String firstName;
	protected String LastName;
	
	public User(String username, String password, String firstName, String lastName){
		this.firstName = firstName;
		this.LastName = lastName;
		this.username = username;
		this.password = password;
		
	}
	
	public User(){	
	}
	
	protected void setUsername(String username){
		
		this.username = username;
	}
	
	protected String getUsername(){
		return this.username;
	}
	
	protected void setPassword(String password){
		this.password =  password;																									
	}
	
	protected String getPassword(){
		return this.password;
	}
	
	protected void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	protected String getFirstName(){
		return this.firstName;
		
	}
	
	protected void setLastName(String lastName){
		this.LastName = lastName;
	}
	
	protected String getLastName(){
		return this.LastName;
	}
	
	public abstract void viewAllCourses();
	
	public abstract void register() throws IOException;
	
	public abstract Course courseSearch();
	
	

}
