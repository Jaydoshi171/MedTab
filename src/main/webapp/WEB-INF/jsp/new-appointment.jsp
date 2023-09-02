<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

<!DOCTYPE html>
<html>
<head>
	<title>Create Appointment</title>
	<style>
        /* Header styles */
        nav {
        background-color: #333;
        color: #fff;
        font-size: 18px;
        font-weight: bold;
        height: 50px;
        }

        nav ul {
        list-style: none;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: space-between;
        align-items: center;
        height: 100%;
        }

        nav ul li {
        height: 100%;
        }

        nav ul li.right {
        margin-left: auto;
        }

        nav ul li a {
        display: block;
        color: #fff;
        text-decoration: none;
        padding: 0 20px;
        height: 100%;
        line-height: 50px;
        }

        nav ul li a:hover {
        background-color: #444;
        }

		label {
			display: inline-block;
			margin-bottom: 5px;
		}
		select, textarea {
			display: block;
			margin-bottom: 10px;
			padding: 5px;
			border: 1px solid #ccc;
			border-radius: 5px;
			font-size: 16px;
			width: 100%;
			box-sizing: border-box;
		}
		button {
			background-color: #4CAF50;
			color: white;
			padding: 10px 20px;
			border: none;
			border-radius: 5px;
			cursor: pointer;
		}
		button:hover {
			background-color: #3e8e41;
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
    <nav>
		<ul>
		  <li><a href="${pageContext.request.contextPath}/home-patient">Home</a></li>
		  <li><a href="${pageContext.request.contextPath}/bookAppointment">Book Appointment</a></li>
		  <li><a href="${pageContext.request.contextPath}/getPatientAppointments">Appointment History</a></li>
		  <li><a href="${pageContext.request.contextPath}/updatePatient">Edit Profile</a></li>
		  <li class="right"><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
		</ul>
	</nav>
	  
	<h1>Create Appointment</h1>
	<form method="post" action="http://localhost:8081/MedTab1/bookAppointment">
		<label for="selectDoctor">Select Doctor:</label>
		<select id="selectDoctor" name="selectDoctor">
			<option value="">Select a doctor</option>
			<c:forEach items="${allDoctors}" var="doctor">
                <option value="${doctor.id}">${doctor.user.firstName} ${doctor.user.lastName} - ${doctor.department}</option>
            </c:forEach>
		</select>
		<label for="selectDate">Select Time:</label>
        <select id="selectDate"  name="selectDate" onclick="getTimes()">
            <option value="">Select a date</option>
        </select>
		<label for="comment">Comment:</label>
		<textarea id="appointmentDescription" name="appointmentDescription" rows="5" cols="30"></textarea>
		<button type="submit">Create Appointment</button>
	</form>
</body>
</html>



