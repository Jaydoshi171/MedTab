<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

<!DOCTYPE html>
<html>
<head>
	<title>Admin Patients Page</title>
	<style>

body {
    font-family: Arial, sans-serif;
    margin: 0;
}

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
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
}

h1 {
    margin-top: 0;
}

#newPatientBtn {
    margin-bottom: 20px;
}

.patientTile {
    width: 100%;
    margin-bottom: 20px;
    padding: 10px;
    background-color: #f2f2f2;
    border-radius: 5px;
    box-shadow: 0px 1px 2px rgba(0,0,0,0.2);
    cursor: pointer;
}

.patientTile h2 {
    margin-top: 0;
}

.patientTile .patientDetails {
    display: none;
}

.patientTile .patientDetails p {
    margin: 0;
    padding: 0;
}

.patientTile .patientDetails button {
    margin-right: 10px;
}

.modal {
    display: none;
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0,0,0,0.4);
}

.modal-content {
    background-color: #fefefe;
    margin: 10% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
    max-width: 600px;
}

.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}
button {
    margin-right: 5px;
    padding: 5px 10px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

    </style>
</head>
<body>
	<nav>
		<ul>
		  <li><a href="${pageContext.request.contextPath}/home-admin">Home</a></li>
		  <li><a href="${pageContext.request.contextPath}/adminDoctor">Manage Doctors</a></li>
		  <li><a href="${pageContext.request.contextPath}/adminPatient">Manage Patients</a></li>
		  <li class="right"><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
		</ul>
	</nav>
	<div class="container">
		<h1>Manage Patients</h1>
		<a href="${pageContext.request.contextPath}/signup?userType=Patient">Create New Patient</a>
		<div id="patientTiles">
			<c:forEach items="${allPatients}" var="patient">
				<div class="patientTile" id = "${patient.id}" data-patient-id="${patient.id}">
                    <h2>${patient.user.firstName} ${patient.user.lastName}</h2>
                    <div class="patientDetails">
                        <p><strong>Email:</strong> ${patient.user.email}</p>
                        <p><strong>Phone:</strong> ${patient.user.phoneNo}</p>
                        <p><strong>Address:</strong> ${patient.address}</p>
                        <a href="${pageContext.request.contextPath}/updatePatientAdmin?patientid=${patient.id}">Edit</a>
                        
                        <button class="deletePatientBtn" onclick="deleteUser('${patient.id}')">Delete</button>
                    </div>
                </div>
			</c:forEach>
		</div>
	</div>


	<script>

function EditUser(userid){
            $.ajax({
                type: "GET",
                url: "http://localhost:8081/MedTab1/updatePatientAdmin?patientid="+userid,
                data: "json",
                contentType: "application/json",
                success: function (data) {
                    console.log(data)
                },
                error: function (data) {
                    console.log(data)
                },
            });

            const element = document.getElementById(userid);
            element.remove();
        }

        function deleteUser(userid){
            $.ajax({
                type: "GET",
                url: "http://localhost:8081/MedTab1/deletePatient?patientId="+userid,
                data: "json",
                contentType: "application/json",
                success: function (data) {
                    console.log(data)
                },
                error: function (data) {
                    console.log(data)
                },
            });

            const element = document.getElementById(userid);
            element.remove();
        }
// Show/hide patient details when tile is clicked
function togglePatientDetails() {
    let patientTile = this;
    let patientId = patientTile.getAttribute('data-patient-id');
    let patientDetails = patientTile.querySelector('.patientDetails');
    if (patientDetails.style.display === 'none') {
        patientDetails.style.display = 'block';
    } else {
        patientDetails.style.display = 'none';
    }
}
// Initialize page
function init() {
// Generate patient tiles
    // Add event listener for clicking patient tile
    let patientTiles = document.getElementsByClassName('patientTile');
    for (let patientTile of patientTiles) {
        patientTile.addEventListener('click', togglePatientDetails);
    }
}
// Call init() when page is loaded
window.addEventListener('load', init);

    </script>
</body>
</html>
