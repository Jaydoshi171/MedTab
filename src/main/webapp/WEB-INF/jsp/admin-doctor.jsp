<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

<!DOCTYPE html>
<html>
<head>
	<title>Admin Doctors Page</title>
	<style>

body {
    font-family: Arial, sans-serif;
    font-size: 16px;
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
    text-align: center;
}

button {
    background-color: #333;
    color: #fff;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
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

/* Doctor modal */
#doctorModal {
    display: none;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0,0,0,0.4);
    z-index: 1;
}

#doctorModalContent {
    position: relative;
    background-color: #fefefe;
    margin: 15% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 50%;
}

#doctorModalContent h2 {
    margin-top: 0;
}

#doctorModalContent label {
    display: block;
    margin-bottom: 5px;
}

#doctorModalContent input[type=text], 
#doctorModalContent input[type=email], 
#doctorModalContent input[type=tel], 
#doctorModalContent select {
    width: 100%;
    padding: 10px;
    border-radius: 5px;
    border: 1px solid #ccc;
    margin-bottom: 20px;
    font-size: 16px;
}

#doctorModalContent button {
    margin-right: 5px;
    padding: 10px 15px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

#doctorModalContent button:hover {
    background-color: #3e8e41;
}

#doctorModalContent button.cancel {
    background-color: #f44336;
}

#doctorModalContent button.cancel:hover {
    background-color: #da190b;
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
		<h1>Manage Doctors</h1>
        <a href="${pageContext.request.contextPath}/signup?userType=Doctor">Create New Doctor</a>
		<div id="doctorTiles">
			<c:forEach items="${allDoctors}" var="doctor">
				<div class="doctorTile" id = "${doctor.id}" data-doctor-id="${doctor.id}">
					<h2>${doctor.user.firstName} ${doctor.user.lastName}</h2>
					<p><strong>Specialty:</strong> ${doctor.department}</p>
					<div class="doctorDetails">
						<p><strong>Email:</strong> ${doctor.user.email}</p>
						<p><strong>Phone:</strong> ${doctor.user.phoneNo}</p>
                        <a href="${pageContext.request.contextPath}/updateDoctorAdmin?doctorid=${doctor.id}">Edit</a>
						<button class="deleteDoctorBtn" onclick="deleteUser('${doctor.id}')">Delete</button>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<script>

function EditUser(userid){
            $.ajax({
                type: "GET",
                url: "http://localhost:8081/MedTab1/updateDoctorAdmin?doctorid="+userid,
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
			url: "http://localhost:8081/MedTab1/deleteDoctor?doctorId="+userid,
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

// Show/hide doctor details when tile is clicked
function toggleDoctorDetails() {
	// console.log("in function");
	let doctorTile = this;
	let doctorId = doctorTile.getAttribute('data-doctor-id');
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
</html>
