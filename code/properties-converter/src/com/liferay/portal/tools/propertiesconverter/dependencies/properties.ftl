<html>
	<style type="text/css">
		h1 {color:red;}
		h2 {color:blue;}
		h3 {color:darkgreen;}
		#toc {color:#555; border: 1px dotted #555; width: 700px;}
		#toc li {font-size:14px;}
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
					<#if section.title?has_content>
			 			<li>
			 				<a href="${propertiesFileName}.html#${section.title}">${section.title}</a>
			 			</li>
			 		</#if>
				</#list>
			</ul>
		</#if>
		<h2>Properties</h2>
		<#list sections as section>
			<#if section.description?has_content>
				<#list section.description as description>
					<p>${description}</p>
				</#list>
			</#if>
			<#if section.title?has_content>
				<a name="${section.title}"></a><a href="${propertiesFileName}.html">Top of Page</a>
				<h3>${section.title}</h3>
				<#list section.properties as property>
					<#if property.description?has_content>
						<#list property.description as description>
							<p>${description}</p>
						</#list>
					</#if>
					<#if property.propertiesParagraph?has_content>
						<p><pre>${property.propertiesParagraph}</pre></p>
					</#if>
				</#list>
			</#if>
		</#list>
	</body>
</html>