function initialize() {

    var request = $.ajax({
        url: "http://localhost:8080/getAllStaff",
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        data: "1226",
        success: function(result) {
            console.log(result);
            var select = document.getElementById("fstaffID");
            for (var i = 0; i < result.length; i++) {
                select.options[select.options.length] = new Option(result[i]["employee_ID"], i);
            }
        }
    });
}


function postData(){
    var select = document.getElementById("fstaffID");
    var employeeID = select.options[select.selectedIndex].text;
    console.log(employeeID)

    var request = $.ajax({
        url: "http://localhost:8080/viewStaffLeaves",
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        data: employeeID,
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
                cell1.innerHTML = result[i]["lr_EmployeeID"];
                cell2.innerHTML = result[i]["lr_Duration"];
                cell3.innerHTML = result[i]["lr_Type"];
                cell4.innerHTML = result[i]["leave_Request_Date"];
                cell5.innerHTML = result[i]["leave_End_Date"];
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
    let lrIdString = lrId.toString();

    var request = $.ajax({
        url: "http://localhost:8080/denyLeaveRequest",
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        data: lrIdString,
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
    let lrIdString = lrId.toString();

    var request = $.ajax({
        url: "http://localhost:8080/acceptLeaveRequest",
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        data: lrIdString,
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


