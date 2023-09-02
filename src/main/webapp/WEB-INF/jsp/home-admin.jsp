<!DOCTYPE html>
<html>
<head>
	<title>Admin Home Page</title>
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
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
        }

        h1 {
            text-align: center;
        }

        @media screen and (max-width: 480px) {
            .container {
                padding: 10px;
            }
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
		<h1>Welcome to the Admin Home Page</h1>
		<p>Select a category from the navigation bar above to get started.</p>
	</div>
</body>
</html>
