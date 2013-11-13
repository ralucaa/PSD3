package DatabaseInteraction;

import java.io.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BarcodeImport {
	ArrayList<String> barcodes = new ArrayList<String>();
	Date d1, d2;
	
	public BarcodeImport(Date d1, Date d2) {
		this.d1 = d1;
		this.d2 = d2;
	}
	
	/** loads barcode numbers which are within date range
		returns number of lines with errors */
	public int load(String filepath) throws FileNotFoundException {
		Scanner fsc = new Scanner(new File(filepath));
		String barcode, date, time;
		SimpleDateFormat parser = new SimpleDateFormat("M/d/y h:m:s a");
		int errors = 0;
		while (fsc.hasNextLine()) {
			Scanner lsc = new Scanner(fsc.nextLine());
			lsc.useDelimiter(",");
			try {
				barcode = lsc.next();
				lsc.next();
				time = lsc.next();
				date = lsc.next();
				Date d = parser.parse(date + " " + time);
				if (d == null) {
					errors ++;
				} else if (d.compareTo(d1) >= 0 && d.compareTo(d2) <= 0) {
					barcodes.add(barcode);
				}
			} catch (NoSuchElementException e) {
				errors ++;
			} catch (ParseException e) {
				errors ++;
			}
		}
		return errors;
	}
	
	public List<String> getBarcodes() {
		return barcodes;
	}
	
	/**test*/
	public static void main(String[] args) throws FileNotFoundException {
		Date d1 = new GregorianCalendar(2013, 1, 1).getTime();
		Date d2 = new Date();
		BarcodeImport bi = new BarcodeImport(d1, d2);
		System.out.println("errors: " + bi.load(args[0]));
		for (String s: bi.getBarcodes()) {
			System.out.println(s);
		}
	}
}

