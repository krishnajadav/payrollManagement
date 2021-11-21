function initialize() {

}

function postData() {
    confirm(document.getElementById("femployeeID").value);
    var employeeID = document.getElementById("femployeeID").value;
    var leaveDuration = document.getElementById("fLeaveDuration").value;
    var leaveTypeString = document.getElementById("fleaveTypeID").value;
    var leaveStartdate = new Date(document.getElementById("fLeaveStartdate").value).getTime();
    var leaveEnddate = new Date(document.getElementById("fleaveEndDate").value).getTime();



    console.log('1');
    var request = $.ajax({
        url: "http://localhost:8080/getEmployeeLeaves",
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        // data: "hellow",
        data: JSON.stringify({
            'EmployeeID': employeeID,
            'LeaveDuration': leaveDuration,
            'LeaveTypeID': leaveTypeString,
            'LeaveStartdate': leaveStartdate,
            'isAccepted' : false,
            'LeaveEndDate': leaveEnddate
        }),
        success: function(result) {
            console.log("This is console");
            console.log(result);
            // if(result.error == null){
            if(result){
                var empcode = document.getElementById('femployeeID');
                empcode.value = result.employeeID;
                document.getElementById('femployeeID').disabled = true;
                document.getElementById('fLeaveDuration').disabled = true;
                document.getElementById('fleaveTypeID').disabled = true;
                document.getElementById('fLeaveStartdate').disabled = true;
                document.getElementById('fleaveEndDate').disabled = true;
            } else{
                var errordiv = document.getElementById('div-error-info');
                errordiv.innerHTML = '<p style="color:red;">'+result.error+'</p>;';
            }
        }, error: function(err) {
            console.log(err);
        }
    });
    //
    // request.fail(function(jqXHR, textStatus) {
    //     confirm("Request failed: " + textStatus);
    // });
}