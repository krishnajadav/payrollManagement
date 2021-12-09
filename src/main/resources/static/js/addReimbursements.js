function initialize() {

	var request = $.ajax({
		url: window.location.protocol + "//" + window.location.host + "/viewMyReimbursements",
		type: "POST",
		contentType: 'application/json; charset=utf-8',
		success: function(result) {
			var table = document.getElementById("fviewReimbursements");
			for (var i = 0; i < result.length; i++) {
				var row = table.insertRow(i);
				var cell0 = row.insertCell(0);
				var cell1 = row.insertCell(1);
				var cell2 = row.insertCell(2);
				var cell3 = row.insertCell(3);
				var cell4 = row.insertCell(4);
				
				
				
				cell0.innerHTML = result[i]["rrtypeID"];
				cell1.innerHTML = result[i]["rrNote"];
				cell2.innerHTML = result[i]["rrAmount"];
				cell3.innerHTML = result[i]["rrDate"];
				cell4.innerHTML = result[i]["isAccepted"];


			}
		}
	});
}

function postData() {
	var type = document.getElementById("freimbursementTypeID").value;
	var note = document.getElementById("freimbursementNote").value;
	var ammount = document.getElementById("freimbursementAmount").value;
	var date = String((new Date(document.getElementById("freimbursementDate").value)).getTime());

	var request = $.ajax({
		url: window.location.protocol + "//" + window.location.host + "/getReimbursementRequest",
		type: "POST",
		contentType: 'application/json; charset=utf-8',
		data: JSON.stringify({
			'RR_TypeID': type,
			'RR_Note': note,
			'RR_Amount': ammount,
			'RR_Date': date
		}),
		success: function(result) {
			if (result.error == null) {
				document.getElementById('freimbursementNote').disabled = true;
				document.getElementById('freimbursementTypeID').disabled = true;
				document.getElementById('freimbursementAmount').disabled = true;
				document.getElementById('freimbursementDate').disabled = true;
				var errordiv = document.getElementById('div-error-info');
				errordiv.innerHTML = "<p style='color:Green;'>Insert Successfully</p>;";
			} else {
				var errordiv = document.getElementById('div-error-info');
				errordiv.innerHTML = "<p style='color:red;'>" + result.error + '</p>;';
			}
		}, error: function(err) {
			console.log(err);
		}
	});
}