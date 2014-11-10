var createUserURL = "http://127.0.0.1:8080/pfm/rest/users/createUser";

function buildUser() {
	var name = $(name).value;
	var lastname = $(lastname).value;
	var username = $(username).value;
	var password = $(password).value;
	var gender = $(gender).value;
	var email = $(email).value;
	var city = $(city).value;
	var address = $(address).value;
	var phone = $(phone).value;
	
	var userJSON = {
		"email" : email,
		"firstname" : name,
		"lastname" : lastname,
		"un" : username,
		"password" : password,
		"gender" : gender,
		"hometown" : city,
		"address" : address,
		"phone" : phone,
		"roles" : [
			"user"
		]
	};
	
	return userJSON;
}

function createUser() {
	
	var user = buildUser();
	alert(user);
	
	$.ajax({
        type: "POST",
        url: createUserURL,
        // The key needs to match your method's input parameter (case-sensitive).
        data: user,
        //contentType: "application/json;charset=UTF-8",
        dataType: "json",
        success: function (data) { 
			alert(data);
		},
        failure: function (errMsg) {
            alert(errMsg);
        }
    });
	
}