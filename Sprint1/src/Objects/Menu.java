package Objects;

import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

/** class to make text menus easier and more consistent */
public final class Menu<T> {
	private LinkedHashMap<String,T> options = new LinkedHashMap<String,T>();
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
	 * @param item - item to store - toString should give the option description
	 */
	public void add(String key, T item) {
		options.put(key, item);
	}
	
	public T get(String key) {
		return options.get(key);
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

