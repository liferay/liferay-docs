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
			<#list htmlFiles as htmlFile>
				<li>
					<a href="${htmlFile.fileName}" title="${htmlFile.fileName}">${htmlFile.propertiesFileName}</a>
				</li>
			</#list>
		</ul>
	</body>
</html>