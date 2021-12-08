function initialize() {
	var empID = document.getElementById("femployeeID").innerHTML;
	var request = $.ajax({
		url: window.location.protocol + "//" + window.location.host + "/employeeNamesWithID",
		type: "POST",
		contentType: 'application/json; charset=utf-8',
		data: empID,
		success: function(result) {
			var employees = document.getElementById("femployee");
			for (var i = 0; i < result.length; i++) {
				var option = document.createElement("option");
				option.text = result[i];
				employees.add(option);
			}
		}
	});
	request.fail(function(jqXHR, textStatus) {
		confirm("Request failed: " + textStatus);
	});
}

function loadValues() {
	var employee = document.getElementById("femployee").value;
	var empID = employee.substring(employee.indexOf('(') + 1, employee.indexOf(')'));

	var request = $.ajax({
		url: window.location.protocol + "//" + window.location.host + "/getSelfAppraisalInfo",
		type: "POST",
		contentType: 'application/json; charset=utf-8',
		data: empID,
		success: function(result) {

			while (document.getElementById("fmanagerreview").rows.length > 1) {
				document.getElementById("fmanagerreview").deleteRow(1);
			}
			while (document.getElementById("fselfreview").rows.length > 1) {
				document.getElementById("fselfreview").deleteRow(1);
			}
			while (document.getElementById("fprojectsparticipated").rows.length > 1) {
				document.getElementById("fprojectsparticipated").deleteRow(1);
			}
			while (document.getElementById("ftechnologieslearned").rows.length > 1) {
				document.getElementById("ftechnologieslearned").deleteRow(1);
			}

			if (result[1] == null) {
				var table = document.getElementById("fmanagerreview");
				var row = table.insertRow();
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);
				var cell4 = row.insertCell(3);
				cell1.innerHTML = "<label id = 'femployeeID'>" + result[0].employeeID + "</label>";
				cell2.innerHTML = "<label id = 'femployeename'>" + result[0].employeeName + "</label>";
				cell3.innerHTML = "<input type='text' id = 'managerrating' style='border:solid 1px black;'/>";
				cell4.innerHTML = "<textarea rows='4' cols='100%' id = 'managercomments' style='border:solid 1px black;'/>";


				var table = document.getElementById("fselfreview");
				var row = table.insertRow();
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);
				var cell4 = row.insertCell(3);
				cell1.innerHTML = result[0].employeeID;
				cell2.innerHTML = result[0].employeeName;
				cell3.innerHTML = result[0].rating;
				cell4.innerHTML = result[0].comments;


				for (var j = 0; j < result[0].projectsParticipated.length; j++) {
					var table = document.getElementById("fprojectsparticipated");
					var row = table.insertRow();
					var cell1 = row.insertCell(0);
					var cell2 = row.insertCell(1);
					var cell3 = row.insertCell(2);
					cell1.innerHTML = result[0].projectsParticipated[j];
					cell2.innerHTML = "<select><option></option><option>Small scale</option><option>Medium scale</option><option>Large scale</option></select>";
					cell3.innerHTML = "<input type='text' id = 'managerrating' style='border:solid 1px black;'/>";
				}


				var table = document.getElementById("ftechnologieslearned");
				var row = table.insertRow();
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);
				var cell4 = row.insertCell(3);
				cell1.innerHTML = result[0].employeeID;
				cell2.innerHTML = result[0].employeeName;
				cell3.innerHTML = result[0].technologiesLearnt;
				cell4.innerHTML = "<input type='text' id ='fcommunicationrating' style='border:solid 1px black;'/>";
			}
			else {
				var errordiv = document.getElementById('div-error-info');
				errordiv.innerHTML = "<p style='color:red'>" + result[1] + "</p>";
			}
		}
	});
	request.fail(function(jqXHR, textStatus) {
		confirm("Request failed: " + textStatus);
	});

}

function addRow() {
	var table = document.getElementById("fprojectsparticipated");
	var row = table.insertRow();
	var cell1 = row.insertCell(0);
	cell1.innerHTML = "<td><input type='text' style='border:solid 1px black;'/></td>";
}

function postData() {
	var errordiv = document.getElementById('div-error-info');
	var managerRating = document.getElementById("managerrating").value;
	var managerComments = document.getElementById("managercomments").value;
	var projectsParticipated = [];
	var employeeName = document.getElementById("femployeename").textContent;
	var employeeID = document.getElementById("femployeeID").textContent;
	var communicationRating = document.getElementById("fcommunicationrating").value;
	errordiv.innerHTML = "";

	for (var i = 1; i < document.getElementById("fprojectsparticipated").rows.length; i++) {
		var project = document.getElementById("fprojectsparticipated").rows[i].cells[0].innerText;
		var scale = document.getElementById("fprojectsparticipated").rows[i].cells[1].children[0].value;
		var contribution = document.getElementById("fprojectsparticipated").rows[i].cells[2].children[0].value;
		if (project.trim()) {
			projectsParticipated.push({ projectName: project.trim(), projectSize: scale.trim(), employeeContribution: contribution.trim() });
		}
	}
	var request = $.ajax({
		url: window.location.protocol + "//" + window.location.host + "/submitReviewData",
		type: "POST",
		contentType: 'application/json; charset=utf-8',
		data: JSON.stringify({
			'rating': managerRating,
			'comments': managerComments,
			'communicationSkillsRating': communicationRating,
			'projectsParticipated': projectsParticipated,
			'employeeName': employeeName,
			'employeeID': employeeID
		}),
		success: function(result) {
			var errordiv = document.getElementById('div-error-info');
			if (result[1] == null) {
				if (result[0].error == null) {
					errordiv.innerHTML = "<p style='color:green'>Successfully submitted the Manager Review</p>";
					disableForm();
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