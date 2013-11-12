import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StudentAttendance {
	
	/* course X sessionType X username */
	//If we dont use a database here then we have to assume that these records cannot be changed
	private static final String[] MARTYNAS_PSD3_LAB_ATTENDANCE = {};
	private static final String[] HELEN_PSD3_LAB_ATTENDANCE = {"2013-09-01"};
	private static final String[] RALUCA_PSD3_LAB_ATTENDANCE = {"2013-09-01", "2013-09-08"};
	private static final String[] VLAD_PSD3_LAB_ATTENDANCE = {"2013-09-01", "2013-09-08", "2013-09-15"};
	private static final String[] TOMASZ_PSD3_LAB_ATTENDANCE = {"2013-09-01", "2013-09-08", "2013-09-15", "2013-09-22"};

		
	
	/*
	 * @param	course      the name of the course we want to retrieve information about, i.e. PSD3, AP3, etc.
	 * @param	sessionType what kind of session is this, i.e. lab, tutorial, lecture, etc.
	 * @param	username    the username of the student we are looking to retrieve information about
	 * @return                   an array of dates that correspond to lab sessions attended
	 */
	public String[] getAttendanceByCourse(String courseName, String sessionType, String studentName) {
		
		if (courseName.equalsIgnoreCase("PSD3")) {
			
			if (sessionType.equalsIgnoreCase("lab")) {
				
				if (studentName.equalsIgnoreCase("martynas"))
					return MARTYNAS_PSD3_LAB_ATTENDANCE;
				else if (studentName.equalsIgnoreCase("helen"))
					return HELEN_PSD3_LAB_ATTENDANCE;
				else if (studentName.equalsIgnoreCase("raluca"))
					return RALUCA_PSD3_LAB_ATTENDANCE;
				else if (studentName.equalsIgnoreCase("vlad"))
					return VLAD_PSD3_LAB_ATTENDANCE;		
				else if (studentName.equalsIgnoreCase("tomasz"))
					return TOMASZ_PSD3_LAB_ATTENDANCE;
				
			}			
		}
		
		/* Oh snap! */
		return null;
		
	}
	
	//That is the most ugly code I have ever written xD  This whole class has to be changed or we need a database.
	//Write attendance from all the courses that selected student attended
	public void generateCsvFile(String studentName,String courseName,String sessionType,String fileNameCSV){
		String[] records = this.getAttendanceByCourse(courseName,sessionType,studentName);
		try{
			FileWriter writer = new FileWriter(fileNameCSV);
			if (courseName.equalsIgnoreCase("PSD3") && sessionType.equalsIgnoreCase("lab")) {
				writer.append("PSD3");
				writer.append(',');
				writer.append("lab");
				writer.append(',');
		  		for(String record: records){
			    		writer.append(record);
			    		writer.append(',');
				}
		    		writer.append('\n');
	 
		    		writer.flush();
		    		writer.close();
		    		
			}
			}
			catch(IOException e){
		     		e.printStackTrace();
			} 
		} 	

}

