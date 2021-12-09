function initialize() {
	var request = $.ajax({
		url: window.location.protocol + "//" + window.location.host + "/getDesignations",
		type: "POST",
		contentType: 'application/json; charset=utf-8',
		success: function(result) {
			var designations = document.getElementById("fdesignation");
			for (var i = 0; i < result.length; i++) {
				var option = document.createElement("option");
				option.text = result[i];
				designations.add(option);
			}
		}
	});

	request.fail(function(jqXHR, textStatus) {
		confirm("Request failed: " + textStatus);
	});

	var requestManager = $.ajax({
		url: window.location.protocol + "//" + window.location.host + "/getManagerNames",
		type: "POST",
		contentType: 'application/json; charset=utf-8',
		success: function(result) {
			var managers = document.getElementById("fassignmanager");
			for (var i = 0; i < result.length; i++) {
				var option = document.createElement("option");
				option.text = result[i];
				managers.add(option);
			}
		}
	});

	requestManager.fail(function(jqXHR, textStatus) {
		confirm("Request failed: " + textStatus);
	});

	var requestManager = $.ajax({
		url: window.location.protocol + "//" + window.location.host + "/getDepartments",
		type: "POST",
		contentType: 'application/json; charset=utf-8',
		success: function(result) {
			var departments = document.getElementById("fdepartment");
			for (var i = 0; i < result.length; i++) {
				var option = document.createElement("option");
				option.text = result[i];
				departments.add(option);
			}
		}
	});

	requestManager.fail(function(jqXHR, textStatus) {
		confirm("Request failed: " + textStatus);
	});
}

function postData() {
	var errordiv = document.getElementById('div-error-info');
	var fullname = document.getElementById("fname").value;
	var salary = document.getElementById("fsalary").value;
	var designation = document.getElementById("fdesignation").value;
	var joiningdate = document.getElementById("fjoiningdate").value;
	var managerInfo = document.getElementById("fassignmanager").value;
	var managername = String(managerInfo).substring(0, managerInfo.indexOf('(') - 1);
	var managerID = String(managerInfo).substring(managerInfo.indexOf('(') + 1, managerInfo.length - 1);
	var departmentInfo = document.getElementById("fdepartment").value;
	var departmentName = String(departmentInfo).substring(0, departmentInfo.indexOf('(') - 1);
	var departmentID = String(departmentInfo).substring(departmentInfo.indexOf('(') + 1, departmentInfo.length - 1);
	var accessLevel = document.getElementById("faccesslevel").value;
	errordiv.innerHTML = "";
	var request = $.ajax({
		url: window.location.protocol + "//" + window.location.host + "/getEmployeeData",
		type: "POST",
		contentType: 'application/json; charset=utf-8',
		data: JSON.stringify({
			'fullName': fullname,
			'employeeSalary': salary,
			'employeeDesignation': designation,
			'employeeJoiningDate': joiningdate,
			'managerName': managername,
			'managerID': managerID,
			'departmentName': departmentName,
			'departmentID': departmentID,
			'accessLevel': accessLevel
		}),
		success: function(result) {
			console.log(result[0]);
			if (result[1] == null) {
				if (result[0].error == null) {
					var empcode = document.getElementById('femployeecode');
					console.log(result[0].employeeID);
					empcode.value = result[0].employeeID;
					document.getElementById('fname').disabled = true;
					document.getElementById('fsalary').disabled = true;
					document.getElementById('fdesignation').disabled = true;
					document.getElementById('fjoiningdate').disabled = true;
					document.getElementById('fassignmanager').disabled = true;
					document.getElementById('fdepartment').disabled = true;
					errordiv.innerHTML = "<p style='color:green'>Successfully added the employee data into DB</p>";
				} else {
					errordiv.innerHTML = "<p style='color:red'>" + result[0].error + "</p>";
				}
			} else {
				errordiv.innerHTML = "<p style='color:red'>" + result[1] + "</p>";
			}
		}
	});

	request.fail(function(jqXHR, textStatus) {
		confirm("Request failed: " + textStatus);
	});
}