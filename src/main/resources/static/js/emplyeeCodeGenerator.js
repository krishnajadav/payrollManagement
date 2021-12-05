function initialize() {

}

function postData() {
	confirm(document.getElementById("fname").value);
	var fullname = document.getElementById("fname").value;
	var salary = document.getElementById("fsalary").value;
	var designation = document.getElementById("fdesignation").value;
	var joiningdate = document.getElementById("fjoiningdate").value;
	var manager = document.getElementById("fassignmanager").value;
	var request = $.ajax({
		url: "http://localhost:8080/getEmployeeData",
		type: "POST",
		contentType: 'application/json; charset=utf-8',
		data: JSON.stringify({
			'fullName': fullname,
			'employeeSalary': salary,
			'employeeDesignation': designation,
			'employeeJoiningDate': joiningdate,
			'employeeManager': manager
		}),
		success: function(result) {
			if(result.error == null){
			var empcode = document.getElementById('femployeecode');
			empcode.value = result.employeeID;
			document.getElementById('fname').disabled = true;
			document.getElementById('fsalary').disabled = true;
			document.getElementById('fdesignation').disabled = true;
			document.getElementById('fjoiningdate').disabled = true;
			document.getElementById('fassignmanager').disabled = true;
			} else{
			var errordiv = document.getElementById('div-error-info');
			errordiv.innerHTML = '<p style="color:red;">'+result.error+'</p>;';
			}
		}
	});

	request.fail(function(jqXHR, textStatus) {
		confirm("Request failed: " + textStatus);
	});
}