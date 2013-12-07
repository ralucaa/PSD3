<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="uk.ac.gla.psd3.Session" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>View sessions | Undergraduate Teaching System</title>

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
	
	        <h1>View sessions</h1>
	        
	        <div class="accordion">
	            <h3>Today</h3>
	            <div>
					<c:forEach var="item" items="${session_array[0]}">  
						<p>
						<c:forEach var="item2" items="${item}">  
						    <c:out value="${item2}"/><br/> 				    
						</c:forEach> 
						</p>
					</c:forEach>  
	            </div>
	            <h3>This week</h3>
	            <div>
					<c:forEach var="item" items="${session_array[1]}">  
						<p>
						<c:forEach var="item2" items="${item}">  
						    <c:out value="${item2}"/><br/> 				    
						</c:forEach> 
						</p>
					</c:forEach>            
	            </div>
	            <h3>All time</h3>
	            <div>
					<c:forEach var="item" items="${session_array[2]}">  
						<p>
						<c:forEach var="item2" items="${item}">  
						    <c:out value="${item2}"/><br/> 				    
						</c:forEach> 
						</p>
					</c:forEach>
	            </div>
	        </div>
	        
	        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	        <script src="<%=request.getContextPath()%>/resources/js/vendor/jquery-ui.min.js"></script>
	        <script src="<%=request.getContextPath()%>/resources/js/plugins.js"></script>
	        <script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
		</div>
    </body>
</html>
