package uk.gla.ac.psd3.Prototype2;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class XController {
	
	@RequestMapping("/setupsession")
	@ResponseBody
	String setupSession(){
		return "Setup session shown.";
	}
	
	@RequestMapping("/viewsessions")
	@ResponseBody
	String viewSessions(){
		return "View sessions shown.";
	}
}
