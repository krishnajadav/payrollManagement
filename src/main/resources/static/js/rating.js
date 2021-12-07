function initialize() {
	var request = $.ajax({
		url: "http://localhost:8080/getRatingInfo",
		type: "POST",
		contentType: 'application/json; charset=utf-8',
		data: "26118",
		success: function(result) {
			if (result != null) {
				if (result[1] == null) {
					document.getElementById("ffinalrating").value = result[0].finalRating;

					var table = document.getElementById("fmanagerreview");
					var row = table.insertRow();
					var cell1 = row.insertCell(0);
					var cell2 = row.insertCell(1);
					var cell3 = row.insertCell(2);
					var cell4 = row.insertCell(3);
					cell1.innerHTML = result[0].managerID;
					cell2.innerHTML = result[0].managerName;
					cell3.innerHTML = result[0].managerRating;
					cell4.innerHTML = result[0].managerComments;

					table = document.getElementById("fmanagerreview");
					row = table.insertRow();
					cell1 = row.insertCell(0);
					cell2 = row.insertCell(1);
					cell3 = row.insertCell(2);
					cell4 = row.insertCell(3);
					cell1.innerHTML = result[0].employeeID;
					cell2.innerHTML = result[0].employeeName;
					cell3.innerHTML = result[0].rating;
					cell4.innerHTML = result[0].comments;
				}
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