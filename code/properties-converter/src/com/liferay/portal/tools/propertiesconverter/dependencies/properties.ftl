<html>
	<style type="text/css">
		h1 {color:red;}
		h2 {color:blue;}
		h3 {color:darkgreen;}
		#toc {color:#555; border: 1px dotted #555; width: 700px;}
		#toc li {font-size:14px;}
		pre {background-color: #efe;}
		.inactive {background-color: #eee;}
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
					<#if section._title?has_content>
			 			<li>
			 				<a href="${propertiesFileName}.html#${section._title}">${section._title}</a>
			 			</li>
			 		</#if>
				</#list>
			</ul>
		</#if>
		<h2>Properties</h2>
		<#list sections as section>
			<#if section._title?has_content>
				<hr></hr>
				<a name="${section._title}"></a><a href="${propertiesFileName}.html">Top of Page</a>
				<h3>${section._title}</h3>
			</#if>
			<#if section._descriptionParagraphs?has_content>
				<#list section._descriptionParagraphs as paragraph>
					<p>${paragraph}</p>
				</#list>
			</#if>
			<#if section._propertiesParagraphs?has_content>
				<hr></hr>
				<#list section._propertiesParagraphs as paragraph>
					<p>${paragraph}</p>
				</#list>
			</#if>
			<#if section._activeProperties?has_content>
				<em>Defaults:</em>
				<p><pre>${section._activeProperties}</pre></p>
			</#if>
			<#if section._inactiveProperties?has_content>
				<em>Examples:</em>
				<p><pre class="inactive">${section._inactiveProperties}</pre></p>
			</#if>
		</#list>	
	</body>
</html>