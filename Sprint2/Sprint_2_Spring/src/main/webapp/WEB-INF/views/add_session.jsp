<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Add a new session | Undergraduate Teaching System</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/normalize.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
        <script src="<%=request.getContextPath()%>/resources/js/vendor/modernizr-2.6.2.min.js"></script>
    </head>
    <body>
    	<div class="content">
	        <!--[if lt IE 7]>
	            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
	        <![endif]-->
	
	        <h1>Add a new teaching session</h1>
	        
	        <form action="addsessionresult" method="POST">
	
	            <!-- Course -->
	            <label for="course">Course:</label>
	            <select name="course">
	                <option>Professional Software Development 3</option>
	                <option>Algorithmics 3</option>
	                <option>Advanced Programming 3</option>
	                <option>Programming Languages 3</option>
	                <option>Interactive Systems 3</option>            
	            </select>    
	            <br />
				<!-- Sesion name -->
	            <label for="session_name">Sesion name:</label>
	            <input type="text" name="session_name" />
	            <br />
	            <!-- Date picker -->           
	            <label for="datepicker">Start date:</label>
	            <input type="text" class="date_picker" name="start_date" />
	            <br />
	            
	            <!-- Repeat frequency -->
	            <label for="session_frequency">Repeat every:</label>  
	            <select name="session_frequency" id="session_frequency">
	                <option value="0">One-off event</option>
	                <option value="1">1 week</option>
	                <option value="2">2 weeks</option>
	                <option value="3">3 weeks</option>
	                <option value="4">4 weeks</option>
	            </select>
	            <br />
	            <div id="repeat_until_wrapper" class="hidden">
	                <label for="end_date">Repeat until:</label>  
	                <input type="text" class="date_picker" name="end_date" />
	                <br />
	            </div>
	
	            <!-- Time -->
	            <label for="start_time">Start time:</label>
	            <select name="start_time">
	                <option value="09:00">09:00</option>
	                <option value="10:00">10:00</option>
	                <option value="11:00">11:00</option>
	                <option value="12:00">12:00</option>
	                <option value="13:00">13:00</option>
	                <option value="14:00">14:00</option>
	                <option value="15:00">15:00</option>
	                <option value="16:00">16:00</option>
	                <option value="17:00">17:00</option>
	                <option value="18:00">18:00</option>
	                <option value="19:00">19:00</option>
	                <option value="20:00">20:00</option>                
	            </select>
	            <br />
	            <!-- Duration -->
	            <label for="session_duration">Duration:</label>
	            <select name="session_duration">
	                <option value="15">15m</option>
	                <option value="30">30m</option>
	                <option value="45">45m</option>
	                <option value="60" selected>1h</option>
	                <option value="75">1h 15m</option>
	                <option value="90">1h 30m</option>
	                <option value="105">1h 45m</option>
	                <option value="120">2h</option>
	                <option value="135">2h 15m</option>
	                <option value="150">2h 30m</option>
	                <option value="165">2h 45m</option>
	                <option value="180">3h</option>
	                <option value="195">3h 15m</option>
	                <option value="210">3h 30m</option>
	                <option value="225">3h 45m</option>
	                <option value="240">4h</option>
	                <option value="255">4h 15m</option>
	                <option value="270">4h 30m</option>
	                <option value="285">4h 45m</option>
	                <option value="300">5h</option>                
	            </select>
	            <br />
	            <!-- Lecturer -->
	            <label for="staff_member">Staff member:</label>
	            <select name="staff_member">
	                <option>Stephen Brewster</option>
	                <option>Matthew Chalmers</option>  
	                <option>Gethin Norman</option>
	                <option>Jeremy Singer</option>
	                <option>Joseph Sventek</option>
	                <option>David Watt</option>
	            </select>    
	            <br />
	            <!-- Max attendance -->
	            <label for="max_attendance">Max. attendance:</label>
	            <input type="number" name="max_attendance" />
	            <br />
	            <!-- Compulsory -->
	            <label for="compulsory">Is compulsory?</label>
	            <input type="checkbox" name="compulsory" />
	            <br />
	            <!-- Venue -->
	            <label for="venue">Venue:</label>
	            <select name="venue">
	                <option value="Boyd Orr 513">Boyd Orr 513</option>
	                <option value="Boyd Orr 720">Boyd Orr 720</option>
	                <option value="Hunterian Art Gallery">Hunterian Art Gallery</option>
	            </select>
	            <br /><br />
	            <button type="submit" name="submit_data">Add session</button>            
	        </form>
	        
	        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	        <script src="<%=request.getContextPath()%>/resources/js/vendor/jquery-ui.min.js"></script>
	        <script src="<%=request.getContextPath()%>/resources/js/plugins.js"></script>
	        <script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
		</div>
    </body>
</html>
