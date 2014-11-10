<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>PFM- Package for Me</title>
<meta name="keywords" content="package for me" />
<meta name="description" content="package for me" />
<link href="templatemo_style.css" rel="stylesheet" type="text/css" /> 
<link href="fullsize/fullsize.css" media="screen" rel="stylesheet" type="text/css" />
<link href="css/custom-theme/jquery-ui-1.9.2.custom.css" rel="stylesheet" />
<script src="js/jquery-1.8.3.js"></script>
<script src="js/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="fullsize/jquery.fullsize.minified.js"></script> 
<script src="js/functions.js"></script>      
<script language="javascript" type="text/javascript">

 var map;
var findAllPackagesURL = "http://127.0.0.1:8080/pfm/rest/package/findAllPackages";
function clearText(field)
{
	if (field.defaultValue == field.value) field.value = '';
	else if (field.value == '') field.value = field.defaultValue;
}
$(function(){
				$("div.templatemo_gallery img").fullsize();
			});
			
			
function createRowFilter(firstRow, lastRow){

	var rowFilter = {firstRow:0,lastRow:10};
	return rowFilter;
	
}
			
function initPage() {
	
	 map = mapbox.map('map');
			  map.addLayer(mapbox.layer().id('serhatt.map-balff610'));

			  // Attribute map
			  map.ui.attribution.add()
				  .content('<a href="http://mapbox.com/about/maps">Terms &amp; Feedback</a>');
	
	var rowFilter = createRowFilter(0,50);
	$.ajax({
        type: "POST",
        url: "http://127.0.0.1:8080/pfm/rest/package/findAllPackagesByStatus",
        // The key needs to match your method's input parameter (case-sensitive).
        data: {status:"false",rowFilter:JSON.stringify(rowFilter)},
        //contentType: "application/json;charset=UTF-8",
        dataType: "json",
        success: function (data) { 
		
			var csv = 'lat,lon,title,marker-color\n';
			
			for(x in data.packageList){
				var package_ = data.packageList[x];
				var color_ = resolvePackageColor(package_.type);
				csv = csv + package_.venueRef.longitude+','+package_.venueRef.latitude+','+package_.name+ ' by '+package_.owner.username +' @'+package_.venueRef.name +','+color_+'\n';
			}
			// Create a markers layer with features from a CSV string.
			  // If you have a remote CSV file (on the same server, though,
			  // due to the same origin policy
			  // http://en.wikipedia.org/wiki/Same_origin_policy
			  // Then you can use jQuery.ajax or reqwest to do an AJAX request
			  // for its content.
			  var markerLayer = mapbox.markers.layer().csv(csv);

			  // Add interaction to this marker layer. This
			  // binds tooltips to each marker that has title
			  // and description defined.
			  mapbox.markers.interaction(markerLayer);
			  map.addLayer(markerLayer);

			  map.zoom(10).center({ lat: 41.036, lon: 28.986 });
			
		},
        failure: function (errMsg) {
            alert(errMsg);
        }
    });
	
}

function resolvePackageColor(type_) {
	var color_ = '#04B404';
	if(type_==1) {
		color_ = '#DF0101';
	}
	else if(type_==2){
		color_ = '#04B404'
	}
	else if(type_==3){
		color_ = '#08088A'
	}
	return color_;
}
</script>
<script>
	$(function() {

		
		$( "#loginDialog" ).dialog({
			autoOpen: false,
			width: 300,
			modal: true,
			buttons: [
				{
					text: "Ok",
					click: function() {
						$( this ).dialog( "close" );
					}
				},
				{
					text: "Cancel",
					click: function() {
						$( this ).dialog( "close" );
					}
				}
			]
		});
		
		$( "#signupDialog" ).dialog({
			autoOpen: false,
			height: 300,
            width: 350,
			modal: true,
			buttons: [
				{
					text: "Ok",
					click: function() {
						createUser();
					}
				},
				{
					text: "Cancel",
					click: function() {
						$( this ).dialog( "close" );
					}
				}
			]
		});

		// Link to open the dialog
		$( "#loginButton" ).click(function( event ) {
			$( "#loginDialog" ).dialog( "open" );
			event.preventDefault();
		});
		
		// Link to open the dialog
		$( "#signupButton" ).click(function( event ) {
			$( "#signupDialog" ).dialog( "open" );
			event.preventDefault();
		});
	
		
	});
	</script>
<script src='http://api.tiles.mapbox.com/mapbox.js/v0.6.7/mapbox.js'></script>
  <link href='http://api.tiles.mapbox.com/mapbox.js/v0.6.7/mapbox.css' rel='stylesheet' />
  <style>
    body { margin:0; padding:0; }
    #map { height:470px;overflow:hidden;1030px; }
	
	#dialog-link {
		padding: .4em 1em .4em 20px;
		text-decoration: none;
		position: relative;
	}
	#dialog-link span.ui-icon {
		margin: 0 5px 0 0;
		position: absolute;
		left: .2em;
		top: 50%;
		margin-top: -8px;
	}
	#icons {
		margin: 0;
		padding: 0;
	}
	#icons li {
		margin: 2px;
		position: relative;
		padding: 4px 0;
		cursor: pointer;
		float: left;
		list-style: none;
	}
	#icons span.ui-icon {
		float: left;
		margin: 0 4px;
	}
  </style>
</head>
<body onload="initPage()">
	<div id="templatemo_container"> 
    	<div id="templatemo_header">
        	<div id="templatemo_logo_area">
                <br/>
				<br/>
				<br/>
				
                <div id="templatemo_slogan">Package For Me</div>
				
			</div>
			<table border="0" width="100%">
				<tr>
					<td ></td>
					<td width="180px" align="right">
						
						
						<input id="loginButton" class="button orange medium" type="button" value="LOGIN" />  
						<input id="signupButton" class="button green medium" type="button" value="SIGN UP" />  
						
					</td>
					<td width="80px" align="right">
						<img src="images/templatemo_icon_3.jpg" alt="RSS" />
						&nbsp;&nbsp;
						<img src="images/templatemo_icon_2.jpg" alt="Twitter" />
					</td>
				</tr>
			</table>
				
			
			
           <!-- <div id="templatemo_social">
            	
                <a href="#"><img src="images/templatemo_icon_3.jpg" alt="RSS" /></a>
            	<a href="#"><img src="images/templatemo_icon_2.jpg" alt="Twitter" /></a>
            	
                <form action="#" method="post">
                	  <input type="text" value="SEARCH" name="q" class="field"  title="email" onfocus="clearText(this)" onblur="clearText(this)" />
                	  <input type="submit" name="search" value = "" alt="Search" class="button" title="Subscribe" />
            	</form>
                
            </div>
            
            <div id="templatemo_menu">
                <ul>
                    <li><a href="#" class="current">Home</a></li>
                    <li><a href="#">Portfolio</a></li>
                    <li><a href="#">Services</a></li>
                    <li><a href="#">Testimonial</a></li>
                    <li><a href="#">News  &amp; Events</a></li>
                    <li><a href="#" class="last">Contact</a></li>
                </ul>    	
    		</div>  end of menu -->
            
        </div>
        
        <div id="templatemo_content_area">
        
        	<div id='map'></div>
			<script>
			</script>
					
        </div> <!-- End of content_area -->
        
    </div><!-- End Of Container -->
    <div class="cleaner"></div>
     <div id="templatemo_footer">
        	Copyright � 2012 <a href="#">GTC</a> | Designed by <a href="http://www.templatemo.com" target="_parent">Serhat Tanrikut</a>    
        </div>
<!--  Free CSS Templates by TemplateMo.com  -->
<div id="loginDialog" title="Login">
	<form>
		<table>
			<tr>
				<td><label for="name">Username</label></td>
				<td> <input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" /></td>
			</tr>
			<tr>
				<td><label for="email">Password</label></td>
				<td> <input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all" /></td>
			</tr>
		</table>
    </form>
</div>
<div id="signupDialog" title="Sign Up">
	<p class="validateTips">All form fields are required.</p>
 
    <form action="#">
		<table>
			<tr>
				<td><label for="name">Name</label></td>
				<td> <input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" /></td>
			</tr>
			<tr>
				<td><label for="lastname">Last Name</label></td>
				<td> <input type="text" name="lastname" id="lastname" class="text ui-widget-content ui-corner-all" /></td>
			</tr>
			<tr>
				<td><label for="username">Username</label></td>
				<td> <input type="text" name="username" id="username" class="text ui-widget-content ui-corner-all" /></td>
			</tr>
			<tr>
				<td><label for="gender">Gender</label></td>
				<td> <select name="gender" id="gender">
					<option value="m">Male</option>
					<option value="f">Female</option>
				</select> </td>
			</tr>
			<tr>
				<td> <label for="password">Password</label></td>
				<td> <input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all" /></td>
			</tr>
			<tr>
				<td><label for="email">Email</label></td>
				<td> <input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all" /></td>
			</tr>
			<tr>
				<td> <label for="city">City</label></td>
				<td> <input type="text" name="city" id="city" value="" class="text ui-widget-content ui-corner-all" /></td>
			</tr>
			<tr>
				<td> <label for="address">Address</label></td>
				<td> <input type="text" name="address" id="address" value="" class="text ui-widget-content ui-corner-all" /></td>
			</tr>
			<tr>
				<td> <label for="phone">Phone</label></td>
				<td> <input type="text" name="phone" id="phone" value="" class="text ui-widget-content ui-corner-all" /></td>
			</tr>
		</table>
    </form>
</div>
</body>
</html>