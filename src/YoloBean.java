import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="yoloBean")
@SessionScoped

public class YoloBean {
	
	private static final String[] user = {"admin", "1234"};
	private String username;
	private String password;
	private boolean isLoggedIn;
	
	public String LogIn() {
		if(user.equals(username) && user.equals(password)){
			setLoggedIn(true);
			return "Ok";
		}
		else return "Wrong"; 		
	}
	
	public String LogOut(){
		setLoggedIn(false);
			return "Ok";
		}
	
	public void setUsername(String username){this.username = username;}
	
	public void setPassword(String password){this.password = password;}
	
	public static String[] getUsers() {return user;}
	
	public String getUsername(){return username;}
	
	public String getPassword(){return password;}

	public boolean isLoggedIn() {return isLoggedIn;}

	public void setLoggedIn(boolean isLoggedIn) {this.isLoggedIn = isLoggedIn;}
}



