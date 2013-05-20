<html>
	<style type="text/css">
		h1 {color:red;}
		h2 {color:blue;}
		h3 {color:darkgreen;}
		#toc {color:#555; border: 1px dotted #555; width: 700px;}
		#toc li {font-size:14px;}
		.defaults {background-color: #efe;}
		.examples {background-color: #eee;}
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
					<#if section.hasTitle()>
			 			<li>
			 				<a href="${propertiesFileName}.html#${section.title}">${section.title}</a>
			 			</li>
			 		</#if>
				</#list>
			</ul>
		</#if>
		<h2>Properties</h2>
		<#list sections as section>
			<#if section.hasTitle()>
				<hr></hr>
				<a name="${section.title}"></a><a href="${propertiesFileName}.html">Top of Page</a>
				<h3>${section.title}</h3>
			</#if>
			<#if section.hasComments()>
				<#list section.comments as paragraph>
					<p>${paragraph}</p>
				</#list>
			</#if>
			<#if section.hasPropertyComments()>
				<hr></hr>
				<#list section.propertyComments as paragraph>
					<#if paragraph.isPreFormatted()>
						<p><pre>${paragraph.comment}</pre></p>
					<#else>
						<p>${paragraph.comment}</p>
					</#if>
				</#list>
			</#if>
			<#if section.hasDefaultProperties()>
				<em>Defaults:</em>
				<p><pre class="defaults">${section.defaultProperties}</pre></p>
			</#if>
			<#if section.hasExampleProperties()>
				<em>Examples:</em>
				<p><pre class="examples">${section.exampleProperties}</pre></p>
			</#if>
		</#list>
	</body>
</html>