<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title}</title>

	${theme.include(top_head_include)}
</head>

<body class="portal-popup ${css_class}">

${theme.include(content_include)}

${theme.include(bottom_ext_include)}

</body>

</html>