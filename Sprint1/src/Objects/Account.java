package Objects;

public class Account {
	public static final String TYPE_ADMIN = "Admin", TYPE_TUTOR = "Tutor";
	private final String name, type, password;
	
	public Account(String name, String password, String type){
		this.name = name;
		this.type = type;
		this.password = password;
	}
	
	public String getName(){
		return name;
	}
	
	public String getType(){
		return type;
	}
	
	public boolean checkPassword(String password){
		return this.password.equals(password);
	}
}
