import java.util.*;

/** class to make text menus easier and more consistent */
public class Menu {
	private LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
	private Scanner input = new Scanner(System.in);
	private String description;
	
	public Menu(String description) {
		this.description = description;
	}
	
	/** add an option. key is what the user types; text is the option description */
	public void add(String key, String text) {
		options.put(key, text);
	}
	
	/** print menu description and options, then loop until we get a valid answer */
	public String go() {
		System.out.println("\n" + description);
		Set<String> keys = options.keySet();
		for (String key : keys) {
			System.out.println(key + ". " + options.get(key));
		}
		String choice;
		do {
			System.out.print("? ");
			choice = input.nextLine();
		} while (!options.containsKey(choice));
		return choice;
	}
}

