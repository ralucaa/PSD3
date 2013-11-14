package Objects;

import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

/** class to make text menus easier and more consistent */
public final class Menu {
	private LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
	private Scanner input = new Scanner(System.in);
	private String description;
	
	/**
	 * Constructor for the menu.
	 * @param description - the menu header.
	 */
	public Menu(String description) {
		this.description = description;
	}
	
	/**
	 * Adds a menu option.
	 * @param key - the selection key.
	 * @param text - the option description.
	 */
	public void add(String key, String text) {
		options.put(key, text);
	}
	
	/** 
	 * Prints the menu and loops until the input valid.
	 * @return the selection key.
	 */
	public String show() {
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

