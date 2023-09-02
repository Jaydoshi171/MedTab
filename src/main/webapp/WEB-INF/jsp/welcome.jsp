<!DOCTYPE html>
<html>
<head>
	<title>MedTab Hospital Management System - Welcome</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #34495e;
            color: #fff;
            padding: 1em;
        }

        nav ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
        }

        nav li {
            float: left;
        }

        nav a {
            display: block;
            color: #fff;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        nav a:hover {
            background-color: #ddd;
            color: #000;
        }

        .hero {
            background-image: url("https://images.unsplash.com/photo-1551760499-85ec06e81a9c?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzJ8fGhvc3BpdGFsJTIwbWFuYWdlbWVudCUyMHN5c3RlbXxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80");
            height: 600px;
            background-size: cover;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            text-align: center;
        }

        .hero h1 {
            font-size: 4em;
            color: #fff;
            margin: 0;
        }

        .hero p {
            font-size: 1.5em;
            color: #fff;
        }

        .btn {
            display: inline-block;
            padding: 1em 2em;
            background-color: #3498db;
            color: #fff;
            border: none;
            border-radius: 0.5em;
            text-decoration: none;
            margin-top: 1em;
            transition: background-color 0.3s ease;
        }

        .btn:hover {
            background-color: #2980b9;
        }

        .features {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            align-items: stretch;
            padding: 2em;
        }

        .features h2 {
            font-size: 2.5em;
            margin-bottom: 1em;
            text-align: center;
        }

        .feature {
            border: 1px solid #ccc;
            border-radius: 0.5em;
            padding: 1em;
            margin: 1em;
            width: 30%;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
            align-items: center;
            text-align: center;
        }

        .feature h3 {
            font-size: 1.5em;
            margin-top: 0;
        }

        .feature p {
            font-size: 1em;
            line-height: 1.5em;
        }

        footer {
            background-color: #34495e;
            color: #fff;
            padding: 1em;
            text-align: center;
            font-size: 0.8em;
            margin-top: 2em;
        }
    </style>
</head>
<body>
	<header>
		<nav>
			<ul>
				<li><a href="${pageContext.request.contextPath}/welcome">MedTab</a></li>
				<li><a href="${pageContext.request.contextPath}/login">Login</a></li>
				<li><a href="${pageContext.request.contextPath}/signup?userType=Patient">Sign Up</a></li>
			</ul>
		</nav>
	</header>

	<main>
		
	</main>
</body>
</html>
