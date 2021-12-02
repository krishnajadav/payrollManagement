function initialize() {

    var request = $.ajax({
        url: "http://localhost:8080/viewMyReimbursements/F01234",
        type: "GET",
        contentType: 'application/json; charset=utf-8',
        success: function(result) {
            console.log(result);
            var table = document.getElementById("fviewReimbursements");
            for (var i = 0; i < result.length; i++) {
                var row = table.insertRow(i);
                var cell0 = row.insertCell(0);
                var cell1 = row.insertCell(1);
                var cell2 = row.insertCell(2);
                var cell3 = row.insertCell(3);
                var cell4 = row.insertCell(4);

                cell0.innerHTML = result[i]["ReimbursementTypeID"];
                cell1.innerHTML = result[i]["ReimbursementNote"];
                cell2.innerHTML = result[i]["ReimbursementAmount"];
                cell3.innerHTML = result[i]["ReimbursementDate"];
                cell4.innerHTML = result[i]["isAccepted"];


            }
        }
    });
}

function postData() {
    confirm(document.getElementById("femployeeID").value);
    var employeeID = document.getElementById("femployeeID").value;
    var reimbursementType = document.getElementById("freimbursementTypeID").value;
    var reimbursementNote = document.getElementById("freimbursementNote").value;
    var reimbursementAmount = document.getElementById("freimbursementAmount").value;
    var reimbursementDate = new Date(document.getElementById("freimbursementDate").value).getTime();



    console.log('1');
    var request = $.ajax({
        url: "http://localhost:8080/getEmployeeReimbursements",
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify({
            'EmployeeID': employeeID,
            'ReimbursementTypeID': reimbursementType,
            'ReimbursmentNote': reimbursementNote,
            'ReimbursementAmount': reimbursementAmount,
            'ReimbursementDate': reimbursementDate,
			'isAccepted' : false
        }),
        success: function(result) {
            console.log("This is console");
            console.log(result);
            if(result){
                var empcode = document.getElementById('femployeeID');
                empcode.value = result.employeeID;
                document.getElementById('femployeeID').disabled = true;
                document.getElementById('freimbursementTypeID').disabled = true;
                document.getElementById('freimbursementNote').disabled = true;
                document.getElementById('freimbursementAmount').disabled = true;
                document.getElementById('freimbursementDate').disabled = true;
            } else{
                var errordiv = document.getElementById('div-error-info');
                errordiv.innerHTML = '<p style="color:red;">'+result.error+'</p>;';
            }
        }, error: function(err) {
            console.log(err);
        }
    });
}