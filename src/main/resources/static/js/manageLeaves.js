function initialize() {

    var request = $.ajax({
        url: "http://localhost:8080/viewEmployeeLeaves/F01234",
        type: "GET",
        contentType: 'application/json; charset=utf-8',
        //data: "F01234",
        success: function(result) {
            console.log(result);
            var table = document.getElementById("fviewLeaves");
            for (var i = 0; i < result.length; i++) {
                var row = table.insertRow(i);
                var cell0 = row.insertCell(0);
                var cell1 = row.insertCell(1);
                var cell2 = row.insertCell(2);
                var cell3 = row.insertCell(3);
                var cell4 = row.insertCell(4);
                var cell5 = row.insertCell(5);
                var cell6 = row.insertCell(6);

                let lrId = result[i]["lr_ID"];
                cell0.innerHTML = lrId;
                cell1.innerHTML = result[i]["employeeID"];
                cell2.innerHTML = result[i]["leaveDuration"];
                cell3.innerHTML = result[i]["leaveTypeID"];
                cell4.innerHTML = result[i]["leaveStartdate"];
                cell5.innerHTML = result[i]["leaveEndDate"];
                let acceptRequestString = `"acceptRequest(${lrId})"`;
                let denyRequestString = `"denyRequest(${lrId})"`;

                cell6.innerHTML =
                    "<input type=\"button\" id=\"faccept_"+result[i]["lr_ID"]+"\" data_id=\""+result[i]["lr_ID"]+"\" name=\"faccept\" onClick = "+acceptRequestString+" value=\"Accept\" />\n" +
                    "            <br>\n" +
                    "            <input type=\"button\" id=\"fdeny_"+result[i]["lr_ID"]+"\" data_id=\""+result[i]["LR_ID"]+"\" name=\"fdeny\" onClick = "+denyRequestString+" value=\"Deny\" />";


            }
        }
    });
}

function denyRequest(lrId){
    console.log(lrId);

    var request = $.ajax({
        url: "http://localhost:8080/denyLeaveRequest/"+lrId,
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        data: lrId,
        success: function(result) {
            //console.log("This is console");
            console.log(result);
            // if(result.error == null){
            if(result){
                // var empcode = document.getElementById('faccept_'+lrId);
                // empcode.value = result.employeeID;
                document.getElementById('faccept_'+lrId).disabled = true;
                document.getElementById('fdeny_'+lrId).disabled = true;


            } else{
                var errordiv = document.getElementById('div-error-info');
                errordiv.innerHTML = '<p style="color:red;">'+result.error+'</p>;';
            }
        }, error: function(err) {
            console.log(err);
        }
    });
}
function acceptRequest(lrId){
    console.log(lrId);

    var request = $.ajax({
        url: "http://localhost:8080/acceptLeaveRequest/"+lrId,
        type: "POST",
        contentType: 'application/json; charset=utf-8',
       // data: lrId,
        success: function(result) {
            //console.log("This is console");
            console.log(result);

            if(result){
                document.getElementById('fdeny_'+lrId).disabled = true;
                document.getElementById('faccept_'+lrId).disabled = true;

            } else{
                var errordiv = document.getElementById('div-error-info');
                errordiv.innerHTML = '<p style="color:red;">'+result.error+'</p>;';
            }
        }, error: function(err) {
            console.log(err);
        }
    });
}

