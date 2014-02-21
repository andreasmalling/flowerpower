import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sun.faces.application.ApplicationFactoryImpl;

@ManagedBean(name="YoloBean")
@SessionScoped

public class YoloBean {
	
	private static final String[] user = {"admin", "1234"};
	private String username;
	private String password;
	private boolean isLoggedIn;
	
	public String LogIn() {
		if(user[0].equals(username) && user[1].equals(password)){
			setLoggedIn(true);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isLoggedIn", isLoggedIn);
			System.out.println("Login Succes");
			return "LOGGEDIN";
		}
		System.out.println("Login Failed"); 
		return "WRONGLOGIN"; 		
	}
	
	public String LogOut(){
		setLoggedIn(false);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isLoggedIn", null);
		return "LOGGEDOUT";
	}
	
	public void setUsername(String username){this.username = username;}
	
	public void setPassword(String password){this.password = password;}
	
	public static String[] getUsers() {return user;}
	
	public String getUsername(){return username;}
	
	public String getPassword(){return password;}

	public boolean isLoggedIn() {return isLoggedIn;}

	public void setLoggedIn(boolean isLoggedIn) {this.isLoggedIn = isLoggedIn;}
}



