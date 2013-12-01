package uk.gla.ac.psd3.Prototype2;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class XController {
	
	@RequestMapping(value = "/addsession", method = RequestMethod.POST)
	@ResponseBody
	String addSession(){
		return "Setup session shown.";
	}
	
	@RequestMapping(value = "/viewsessions", method = RequestMethod.GET)
	@ResponseBody
	String viewSessions(@RequestParam(value="student_id", required=true) String student_id, ModelMap model){
		//Get the sessions from the database. If student_id is invalid show an error message.
		
		//Add the Session IDs as parameters to the ModelMap.
		model.addAttribute("student_id", student_id);
		
		return "View sessions shown. Student ID: " + student_id;
	}
}
