<html>
	<head>
		<title>Liferay Portal ${lpVersion} Properties</title>
	</head>
	<body>
		<p>
			<strong>Liferay Portal ${lpVersion} Properties</strong>
		</p>
		
		<p>
			Here is a listing of Liferay Portal ${lpVersion} properties files and properties definition files:
		</p>

		<ul>
			<#list properties as property>
				<li>
					<a href="${property}" title="${property}">${property}</a>
				</li>
			</#list>
		</ul>
	</body>
</html>