function initialize() {

    var request = $.ajax({
        url: "http://localhost:8080/getAllStaffReimbursements",
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        data: "1226",
        success: function(result) {
            console.log(result);
            var select = document.getElementById("fstaffID");
            for (var i = 0; i < result.length; i++) {
                select.options[select.options.length] = new Option(result[i]["Employee_ID"], i);
            }
        }
    });
}


function postData(){
    var select = document.getElementById("fstaffID");
    var employeeID = select.options[select.selectedIndex].text;
    console.log(employeeID)

    var request = $.ajax({
        url: "http://localhost:8080/viewAllStaffReimbursements",
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        data: employeeID,
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

                let lrId = result[i]["lr_ID"];
                cell0.innerHTML = result[i]["RR_TypeID"];
                cell1.innerHTML = result[i]["RR_Note"];
                cell2.innerHTML = result[i]["RR_Amount"];
                cell3.innerHTML = result[i]["RR_Date"];
                let acceptRequestString = `"acceptRequest(${RR_ID})"`;
                let denyRequestString = `"denyRequest(${RR_ID})"`;

                cell4.innerHTML =
                    "<input type=\"button\" id=\"faccept_"+result[i]["RR_ID"]+"\" data_id=\""+result[i]["RR_ID"]+"\" name=\"faccept\" onClick = "+acceptRequestString+" value=\"Accept\" />\n" +
                    "            <br>\n" +
                    "            <input type=\"button\" id=\"fdeny_"+result[i]["RR_ID"]+"\" data_id=\""+result[i]["RR_ID"]+"\" name=\"fdeny\" onClick = "+denyRequestString+" value=\"Deny\" />";


            }
        }
    });


}


function denyRequest(RR_ID){
    console.log(RR_ID);
    let RR_IDString = RR_ID.toString();

    var request = $.ajax({
        url: "http://localhost:8080/rejectReimbursementRequest",
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        data: RR_IDString,
        success: function(result) {
            console.log(result);
            if(result){
                document.getElementById('faccept_'+RR_ID).disabled = true;
                document.getElementById('fdeny_'+RR_ID).disabled = true;


            } else{
                var errordiv = document.getElementById('div-error-info');
                errordiv.innerHTML = '<p style="color:red;">'+result.error+'</p>;';
            }
        }, error: function(err) {
            console.log(err);
        }
    });
}
function acceptRequest(RR_ID){
    console.log(RR_ID);
    let RR_IDString = RR_ID.toString();

    var request = $.ajax({
        url: "http://localhost:8080/acceptReimbursementRequest",
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        data: RR_IDString,
        success: function(result) {
            //console.log("This is console");
            console.log(result);

            if(result){
                document.getElementById('fdeny_'+RR_ID).disabled = true;
                document.getElementById('faccept_'+RR_ID).disabled = true;

            } else{
                var errordiv = document.getElementById('div-error-info');
                errordiv.innerHTML = '<p style="color:red;">'+result.error+'</p>;';
            }
        }, error: function(err) {
            console.log(err);
        }
    });
}


