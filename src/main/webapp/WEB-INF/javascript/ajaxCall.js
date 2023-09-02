
var dis;
var tal;
var vill;
alert("In script");
$(document).ready(function () {
$.ajax({
    type: "GET",
    url: "http://localhost:8081/MedTab1/getDoctors",
    data: "json",
    contentType: "application/json",
    success: function (data) {
        let obj = $.parseJSON(data);
        $.each(obj, function (key, value) {
            $('#selectDoctor').append('<form:option value="' + value.doctor_id + '">' + value.user.firstname + ' ' + value.user.lastname + ' -- ' + value.department + '</form:option>');
        });
    },

    error: function (data) {
        $('#selectDoctor').append('<form:option>Doctors Unavailable</form:option>');
    },
});
alert("after 1 st function");
/*$('#districtlist').trigger("change");*/

$('#districtlist').change(function () {

    $('#appointmentDate').find('option').remove();
    $('#appointmentDate').append('<option>Select appointmentTime</option>');
    
    var doctorSelected = $('#selectDoctor').val();
    alert(doctorSelected);


    $.ajax({
        type: "GET",
        url: "http://localhost:8081/MedTab1/getDates?doctorSelected=" + doctorSelected,
        /*data: 1,*/
        contentType: "application/json",
        success: function (data) {
            let obj = $.parseJSON(data);
            $.each(obj, function (key, value) {
            $('#appointmentDate').append('<form:option value="' + value + '">' + value + '</form:option>');
            });
        },
        error: function (data) {
            $('#appointmentDate').append('<form:option>appointmentDate Unavailable</form:option>');
        },
    });
});
});
