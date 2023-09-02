<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
	<title>Appointment Page</title>
</head>
<body>
    <div>
		<h2>Make an Appointment</h2>
		<form method="post" action="${pageContext.request.contextPath}/bookAppointment">
			<div>
				<label for="selectDoctor">Doctor:</label>
				<select id="selectDoctor" name="doctor" required>
					<option value="">Select a doctor</option>
                    <c:forEach items="${allDoctors}" var="doctor">
                        <option value="${doctor.id}">${doctor.user.firstName} ${doctor.user.lastName} - ${doctor.department}</option>
			        </c:forEach>
				</select>
			</div>
			<div>
				<label for="selectDate">Appointment Date:</label>
				<select id="selectDate"  name="appointmentDate" required>
					<option value="">Select a date</option>
				</select>
			</div>
			<div class="form-group">
				<label for="appointmentDescription">Appointment Description:</label>
				<textarea id="appointmentDescription" name="appointmentDescription" rows="4" required></textarea>
			</div>
			<button type="submit" value="submit">Submit</button>
		</form>
	</div>

    <script>
        $('#selectDoctor').change(function () {
            $('#selectDate').find('option').remove();
            $('#selectDate').append('<option value="">Select appointmentTime</option>');
            
            var doctorSelected = $('#selectDoctor').val();
            $.ajax({
                type: "GET",
                // url: "http://localhost:8081/MedTab1/datesAvailable?doctorSelected=" + doctorSelected,
                url: "${pageContext.request.contextPath}/datesAvailable",
                data: { doctorSelected: doctorSelected },
                /*data: 1,*/
                // contentType: "application/json",
                success: function (data) {
                    $.each(data, function (key, value) {
                    $('#selectDate').append('<option value="' + 1 + '">' + value + '</option>');
                    });
                },
                error: function (data) {
                    $('#selectDate').append('<option >appointmentDate Unavailable</option>');
                },
            });
        });
    </script>
    
</body>
</html>

<!DOCTYPE html>
<html>
<head>
	<title>Appointment Page</title>
    
	<style>
        body {
            font-family: Arial, sans-serif;
            font-size: 16px;
        }

        .appointment-form {
            margin: 0 auto;
            max-width: 500px;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
        }

        .appointment-form h2 {
            margin-top: 0;
            font-size: 28px;
            text-align: center;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-control {
            display: block;
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .form-control:focus {
            outline: none;
            border-color: #007bff;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }

        .form-control:invalid {
            border-color: #dc3545;
        }

        .form-control:invalid:focus {
            box-shadow: 0 0 5px rgba(220, 53, 69, 0.5);
        }

        /* Style the submit button */
        .btn {
            display: block;
            width: 100%;
            padding: 10px;
            font-size: 16px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.2s;
        }

        .btn:hover {
            background-color: #0069d9;
        }

        .error-message {
            margin-top: 5px;
            color: #dc3545;
            font-size: 14px;
        }

        .success-message {
            margin-top: 5px;
            color: #28a745;
            font-size: 14px;
        } 
    </style>

<script>
    function getTimes() {
        $('#selectDate').find('option').remove();
        $('#selectDate').append('<option value="">Select appointmentTime</option>');

        var doctorSelected = $('#selectDoctor').val();
        $.ajax({
            type: "GET",
            url: "${pageContext.request.contextPath}/datesAvailable",
            data: { doctorSelected: doctorSelected },

            success: function (data) {
                $.each(data, function (key, value) {
                $('#selectDate').append('<option value="' + value + '">' + value + '</option>');
                });
            },
            error: function (data) {
                $('#selectDate').append('<option >appointmentDate Unavailable</option>');
            },
        });
    };
</script>


</head>
<body>
    <div class="appointment-form">
		<h2>Make an Appointment</h2>
		<form method="post" action="http://localhost:8081/MedTab1/bookAppointment">
			<div class="form-group">
				<label for="selectDoctor">Doctor:</label>
				<select id="selectDoctor" name="doctor" class="form-control" required>
					<option value="">Select a doctor</option>
                    <c:forEach items="${allDoctors}" var="doctor">
                        <option value="${doctor.id}">${doctor.user.firstName} ${doctor.user.lastName} - ${doctor.department}</option>
			        </c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="selectDate">Appointment Date:</label>
				<select id="selectDate"  name="appointmentDate" class="form-control" onclick="getTimes()" required>
					<option value="">Select a date</option>
				</select>
			</div>
			<div class="form-group">
				<label for="appointmentDescription">Appointment Description:</label>
				<textarea id="appointmentDescription" name="appointmentDescription" class="form-control" rows="4" required></textarea>
			</div>
			<button type="submit" value="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>

    
</body>
</html>
