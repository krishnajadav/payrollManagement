function initialize() {
var empID = document.getElementById("femployeeID").innerHTML;
	var request = $.ajax({
		url: window.location.protocol + "//" + window.location.host +"/getPeerInfo",
		type: "POST",
		contentType: 'application/json; charset=utf-8',
		data: empID,
		success: function(result) {
			var table = document.getElementById("fpeerreview");
			for (var i = 0; i < result.length; i++) {
				var row = table.insertRow();
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);
				var cell4 = row.insertCell(3);
				var data = result[i].split(",")
				cell1.innerHTML = data[0].trim();
				cell2.innerHTML = data[1].trim();
				cell3.innerHTML = "<input type='text' style='border:solid 1px black;'/>";
				cell4.innerHTML = "<textarea rows='4' cols='100%' style='border:solid 1px black;'/>";
			}
		}
	});
	request.fail(function(jqXHR, textStatus) {
		confirm("Request failed: " + textStatus);
	});
}

function addRow(value) {
	if (value == "project") {
		var table = document.getElementById("fprojectsparticipated");
		var row = table.insertRow();
		var cell1 = row.insertCell(0);
		cell1.innerHTML = "<td><input type='text' style='border:solid 1px black;'/></td>";
	}
	else {
		var table = document.getElementById("ftechnologieslearned");
		var row = table.insertRow();
		var cell1 = row.insertCell(0);
		cell1.innerHTML = "<td><input type='text' style='border:solid 1px black;'/></td>";
	}
}

function postData() {
	var errordiv = document.getElementById('div-error-info');
	var selfRating = document.getElementById("selfrating").value;
	var selfComments = document.getElementById("selfcomments").value;
	var projectsParticipated = [];
	var technologiesLearned = [];
	var employeeName = document.getElementById("femployeeName").textContent;
	var employeeID = document.getElementById("femployeeID").textContent;
	errordiv.innerHTML = "";
	
	for (var i = 1; i < document.getElementById("fprojectsparticipated").rows.length; i++) {
		var project = document.getElementById("fprojectsparticipated").rows[i].cells[0].children[0].value;
		if(project.trim()){
		projectsParticipated.push(project.trim());
		}
	}
	for (var i = 1; i < document.getElementById("ftechnologieslearned").rows.length; i++) {
		var tech = document.getElementById("ftechnologieslearned").rows[i].cells[0].children[0].value;
		if(tech.trim()){
		technologiesLearned.push(tech.trim());
		}
	}
	
	var request = $.ajax({
		url: window.location.protocol + "//" + window.location.host +"/submitAppraisalData",
		type: "POST",
		contentType: 'application/json; charset=utf-8',
		data: JSON.stringify({
			'rating': selfRating,
			'comments': selfComments,
			'technologiesLearnt': technologiesLearned,
			'projectsParticipated': projectsParticipated,
			'employeeName': employeeName,
			'employeeID': employeeID
		}),
		success: function(result) {
		if(result[1] == null){
			if (result[0].error == null) {
				disableForm();
				errordiv.innerHTML = "<p style='color:green'>Successfully submitted the Self Appraisal</p>";
			} else {
				errordiv.innerHTML = "<p style='color:red'>" + result[0].error + "</p>";
			}
			} else{
			errordiv.innerHTML = "<p style='color:red'>" + result[1] + "</p>";
			}
		}
	});

	request.fail(function(jqXHR, textStatus) {
		confirm("Request failed: " + textStatus);
	});
}

function disableForm() {
	var inputs = document.getElementsByTagName("input");
	for (var i = 0; i < inputs.length; i++) {
		inputs[i].disabled = true;
	}
	var selects = document.getElementsByTagName("select");
	for (var i = 0; i < selects.length; i++) {
		selects[i].disabled = true;
	}
	var textareas = document.getElementsByTagName("textarea");
	for (var i = 0; i < textareas.length; i++) {
		textareas[i].disabled = true;
	}
	var buttons = document.getElementsByTagName("button");
	for (var i = 0; i < buttons.length; i++) {
		buttons[i].disabled = true;
	}
}