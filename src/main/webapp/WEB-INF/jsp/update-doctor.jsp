<!DOCTYPE html>
<html>
<head>
	<title>Edit Patient Profile</title>
	<style>
        * {
	box-sizing: border-box;
}

body {
	font-family: Arial, sans-serif;
	font-size: 16px;
	margin: 0;
}

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



.container {
	max-width: 600px;
	margin: 0 auto;
	padding: 20px;
}

h1 {
	text-align: center;
}

.form-group {
	margin-bottom: 20px;
}

label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
}

input[type="text"],
input[type="email"],
textarea,
select {
	display: block;
	width: 100%;
	padding: 10px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

input[type="date"] {
	width: auto;
}

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
	color: #dc3545;
	font-size: 14px;
	margin-top: 5px;
}

.success-message {
	color: #28a745;
	font-size: 14px;
	margin-top: 5px;
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
	<div class="container">
		<h1>Edit Patient Profile</h1>
		<form action="${pageContext.request.contextPath}/updateDoctor" method="post">
			<div class="form-group">
				<label for="first-name">First Name:</label>
				<input type="text" id="first-name" name="firstName" value="${doctor.user.firstName}" disabled>
			</div>
			<div class="form-group">
				<label for="last-name">Last Name:</label>
				<input type="text" id="last-name" name="lastName" value="${doctor.user.lastName}" disabled>
			</div>
			<div class="form-group">
				<label for="email">Email:</label>
				<input type="email" id="email" name="email" value="${doctor.user.email}" disabled>
			</div>
            <div class="form-group">
				<label for="mobile">Mobile Number:</label>
				<input type="tel" id="mobile" name="phoneNo" value="${doctor.user.phoneNo}">
			</div>
			
			<div class="form-group">
				<label for="qualification">Qualification:</label>
				<input type="text" id="qualification" name="qualification" value="${doctor.qualification}">
			</div>

            <div class="form-group">
				<label for="department">Department:</label>
				<select id="department" name="department" value="${doctor.department}">
                    <option value="Anesthetics">Anesthetics</option>
                    <option value="Cardiology">Cardiology</option>
                    <option value="General Surgery">General Surgery</option>
                    <option value="Neurology">Neurology</option>
                    <option value="Oncology">Oncology</option>
                    <option value="Orthopedics">Orthopedics</option>
                    <option value="Radiology">Radiology</option>
                </select>
			</div>

			<button type="submit" class="btn">Save Changes</button>
		</form>
	</div>
</body>
</html>
