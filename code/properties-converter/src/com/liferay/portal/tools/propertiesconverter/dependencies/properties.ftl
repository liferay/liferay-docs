<html>
	<style type="text/css">
		h1 {color:red;}
		h2 {color:blue;}
		h3
		{color:darkgreen;}
		h4 {color:white; background-color:red;}
		.content
		{border: 1px dotted #666; padding: 10px;}
		.description {color: #444;}
		.value {background-color: #efe; font-size: 14px; padding: 4px;
		margin: 10px 20px 20px 20px; font-weight:bold;}
		.alt {font-weight:
		normal; background-color: #eef;}
		.hidden {background-color: #eee;}
		#toc {color:#555; border: 1px dotted #555; width: 700px;}
		#toc li
		{font-size:14px;}
		#toc li li {font-size:12px;}
		pre {background-color: #efe;}
	</style>
	<head>
		<title>${pageTitle!"Portal Properties"}</title>
	</head>
	<body>
		<h1>${pageTitle!"Portal Properties"}</h1>
		<#if toc>
			<h2>Table of Contents</h2>
			<ul id="toc">
				<#list sections as section>
			 		<#if section.name?has_content>
			 			<li>
			 				<a href="properties.html#${section.name}">${section.name}</a>
			 			</li>
			 		</#if>
				</#list>
			</ul>
		</#if>
		<h2>Properties</h2>
		<#list sections as section>
			<#if section.isSectionTitle>
				<a name="${section.name}"></a><a href="properties.html">Top of Page</a>
				<h3>${section.name}</h3>
			<#else>
				<#if section.descriptionParagraphs?has_content>
					<#list section.descriptionParagraphs as descriptionParagraph>
						<p>${descriptionParagraph}</p>
					</#list>
				</#if>
				<p><pre>${section.propertiesParagraph}</pre></p>
			</#if>
		</#list>
	</body>
</html>