function initialize() {
	var request = $.ajax({
		url: window.location.protocol + "//" + window.location.host + "/homepageAccessLevel",
		type: "POST",
		contentType: 'application/json; charset=utf-8',
		success: function(result) {
			if (result == 'user') {
				document.getElementById("fhomepageID").innerHTML = "<li><a class='active' href='AddLeaves'>Apply Leave</a></li><li><a class='active' href='AddReimbursement'>Apply Reimbursement</a></li><li><a href='TaxCalculation'>TaxCalculation</a></li><li><a href='appraisal'>Self Review</a></li><li><a href='rating'>View Rating</a></li><li><a id='btnlossgout' href='UserAuthentication/logout'>Logout</a></li>";
			} else if (result == 'admin') {
				document.getElementById("fhomepageID").innerHTML = "<li><a class='active' href='AddLeaves'>Apply Leave</a></li><li><a class='active' href='ManageLeaves'>Manage Leave</a></li><li><a class='active' href='AddReimbursement'>Apply Reimbursement</a></li><li><a class='active' href='ManagerReimbursementView'>Manage Reimbursement</a></li><li><a href='TaxCalculation'>TaxCalculation</a></li><li><a href='appraisal'>Self Review</a></li><li><a href='managerAppraisal'>Add Manager Review</a></li><li><a href='rating'>View Rating</a></li><li><a id='btnlossgout' href='UserAuthentication/logout'>Logout</a></li>";
			} else if (result == 'super admin') {
				document.getElementById("fhomepageID").innerHTML = "<li><a class='active' href='EmployeeCodeGeneration'>Add Employee</a></li><li><a class='active' href='AddLeaves'>Apply Leave</a></li><li><a class='active' href='ManageLeaves'>Manage Leave</a></li><li><a class='active' href='AddReimbursement'>Apply Reimbursement</a></li><li><a class='active' href='ManagerReimbursementView'>Manage Reimbursement</a></li><li><a href='TaxCalculation'>TaxCalculation</a></li><li><a href='appraisal'>Self Review</a></li><li><a href='managerAppraisal'>Add Manager Review</a></li><li><a href='rating'>View Rating</a></li><li><a id='btnlossgout' href='UserAuthentication/logout'>Logout</a></li>";
			}
		}
	});

	request.fail(function(jqXHR, textStatus) {
		confirm("Request failed: " + textStatus);
	});
}