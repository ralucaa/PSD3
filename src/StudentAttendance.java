public class StudentAttendance {
	
	/* course X sessionType X username */
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
	public String[] getAttendanceByCourse(String course, String sessionType, String username) {
		
		if (course.equalsIgnoreCase("PSD3")) {
			
			if (sessionType.equalsIgnoreCase("lab")) {
				
				if (username.equalsIgnoreCase("martynas"))
					return MARTYNAS_PSD3_LAB_ATTENDANCE;
				else if (username.equalsIgnoreCase("helen"))
					return HELEN_PSD3_LAB_ATTENDANCE;
				else if (username.equalsIgnoreCase("raluca"))
					return RALUCA_PSD3_LAB_ATTENDANCE;
				else if (username.equalsIgnoreCase("vlad"))
					return VLAD_PSD3_LAB_ATTENDANCE;		
				else if (username.equalsIgnoreCase("tomasz"))
					return TOMASZ_PSD3_LAB_ATTENDANCE;
				
			}			
		}
		
		/* Oh snap! */
		return null;
		
	} 	
}
