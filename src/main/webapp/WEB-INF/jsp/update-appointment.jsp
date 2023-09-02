<!DOCTYPE html>
<html>
<head>
	<title>Edit Appointment</title>
	<style>
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
		input, textarea {
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
</head>
<body>
	<nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/home-doctor">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/getDoctorAppointments">Appointments</a></li>
            <li><a href="${pageContext.request.contextPath}/updateDoctor">Update Profile</a></li>
            <li class="right"><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
        </ul>
    </nav>
	<h1>Edit Appointment</h1>
	<form method="post" action="${pageContext.request.contextPath}/updateAppointment">
		<label for="patientName">Patient Name:</label>
		<input type="text" id="patientName" name="patientName" value="${appointment.patient.user.firstName} ${appointment.patient.user.lastName}" disabled>
		<label for="patientEmail">Patient Email:</label>
		<input type="email" id="patientEmail" name="patientEmail" value="${appointment.patient.user.email}" disabled>
		<label for="appointmentDate">Appointment Date:</label>
		<input type="text" id="appointmentDate" name="appointmentDate" value="${appointment.appointmentDate}" disabled>
		<label for="appointmentDescription">Appointment Description:</label>
		<textarea id="appointmentDescription" name="appointmentDescription" rows="5" cols="30" disabled>${appointment.appointmentDescription}</textarea>
		<label for="doctorComments">Doctor Comments:</label>
		<textarea id="doctorComments" name="doctorComments" rows="5" cols="30">${appointment.doctorComment}</textarea>
		<label for="prescription">Prescription:</label>
		<textarea id="prescription" name="prescription" rows="5" cols="30">${appointment.prescription}</textarea>
		<button type="submit">Save Changes</button>
	</form>
</body>
</html>
