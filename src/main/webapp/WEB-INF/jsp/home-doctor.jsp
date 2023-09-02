<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
	<title>Doctor Home</title>
	<style>
        * {
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
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

/* Doctor tile view */
#doctorTiles {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    align-items: flex-start;
    margin-top: 20px;
}

.doctorTile {
    width: 100%;
    margin-bottom: 20px;
    padding: 10px;
    background-color: #f2f2f2;
    border-radius: 5px;
    box-shadow: 0px 1px 2px rgba(0,0,0,0.2);
    cursor: pointer;
}

.doctorTile h2 {
    margin-top: 0;
}

.doctorTile p {
    margin: 0;
}

.doctorTile .doctorDetails {
    display: none;
    margin-top: 10px;
}

.doctorTile .doctorDetails p {
    margin-bottom: 5px;
}

.doctorTile button {
    margin-right: 5px;
    padding: 5px 10px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.doctorTile button:hover {
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
    <div class="container">
		<h1>Upcoming Appointments</h1>
		<!-- <button id="newDoctorBtn">Create New Appointment</button> -->
		<div id="doctorTiles">
			<c:forEach items="${upAppointments}" var="appointment">
				<div class="doctorTile" id = "${appointment.id}" data-appointment-id="${appointment.id}">
					<h2>${appointment.patient.user.firstName} ${appointment.patient.user.lastName}</h2>
					<p><strong>Appointment Time:</strong> ${appointment.appointmentDate}</p>
					<div class="doctorDetails">
						<p><strong>Appointment Description:</strong> ${appointment.appointmentDescription}</p>
						<p><strong>Prescription:</strong> ${appointment.prescription}</p>
						<p><strong>Doctor Comments:</strong> ${appointment.doctorComment}</p>
						<!-- <button class="editDoctorBtn">Edit</button>
						<button class="deleteDoctorBtn" onclick="deleteUser('${doctor.id}')">Delete</button> -->
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
    <script>
        // Show/hide doctor details when tile is clicked
                function toggleDoctorDetails() {
                    // console.log("in function");
                    let doctorTile = this;
                    let doctorId = doctorTile.getAttribute('data-appointment-id');
                    let doctorDetails = doctorTile.querySelector('.doctorDetails');
                    if (doctorDetails.style.display === 'none') {
                        doctorDetails.style.display = 'block';
                    } else {
                        doctorDetails.style.display = 'none';
                    }
                }
        
                // Initialize page
                function init() {
        
                    // Add event listener for clicking doctor tile
                    let doctorTiles = document.getElementsByClassName('doctorTile');
                    for (let doctorTile of doctorTiles) {
                        doctorTile.addEventListener('click', toggleDoctorDetails);
                    }
        
                }
        
                // Call init() when page is loaded
                window.addEventListener('load', init);
        
            </script>
</body>
