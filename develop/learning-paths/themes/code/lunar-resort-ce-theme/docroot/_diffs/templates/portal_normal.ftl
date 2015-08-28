<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>

	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	${theme.include(top_head_include)}
</head>

<body class="${css_class} dockbar-split">

<a href="#main-content" id="skip-to-content"><@liferay.language key="skip-to-content" /></a>

${theme.include(body_top_include)}


	<@liferay.dockbar />

<div class="container-fluid" id="wrapper">
	<header id="banner" role="banner">
		<div id="heading">
			<h1 class="site-title">
				<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
					<img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" width="${site_logo_width}" />
				</a>

				<#if show_site_name>
					<span class="site-name" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
						${site_name}
					</span>
				</#if>
			</h1>

			<#if show_page_title>
			<h2 class="page-title">
				<span>${the_title}</span>
			</h2>
			</#if>
		</div>

		<!--*<#if !is_signed_in>
			<a href="${sign_in_url}" data-redirect="${is_login_redirect_required?string}" id="sign-in" rel="nofollow">${sign_in_text}</a>
		</#if>-->

		
			<#include "${full_templates_path}/navigation.ftl" />
		

	</header>

	<div id="content">
		<#if show_breadcrumbs>
		<nav id="breadcrumbs"><@liferay.breadcrumbs /></nav>
		</#if>
		<#if selectable>
			${theme.include(content_include)}
		<#else>
			${portletDisplay.recycle()}

			${portletDisplay.setTitle(the_title)}

			${theme.wrapPortlet("portlet.ftl", content_include)}
		</#if>
	<#if the_title != "Reservation">
	<div id="reserveBtnWrapper">
	<span id="reserveBtn"><a href="/web/guest/reservation">Make Your Reservation!</a></span>
	</div>
	</#if>
	</div>
	<footer id="footer" role="contentinfo">
<div class="social-media-wrapper">
        <h2 class="nav-heading">
            Follow Us
        </h2>
        <ul>
            <li>
                <div id="facebook"><a class="icon-facebook"  
                href="http://www.facebook.com/pages/Liferay/45119213107" 
                target="_blank"><span class="hide">Facebook</span></a></div>
            </li>
            <li>
                <div id="twitter"><a class="icon-twitter" 
 				href="http://www.twitter.com/liferay" 
                target="_blank"><span class="hide">Twitter</span></a></div>
            </li>
            <li>
                <div id="linked-in"><a class="icon-linkedin" 
                href="http://www.linkedin.com/company/83609" 
                target="_blank"><span class="hide">LinkedIn</span></a></div>
            </li>
            <li>
                <div id="youtube"><a class="icon-youtube" 
                href="http://www.youtube.com/user/liferayinc" 
                target="_blank"><span class="hide">YouTube</span></a></div>
            </li>
            <li>
                <div id="google-plus">
                <a class="icon-google-plus" 
                href="https://plus.google.com/+liferay/posts" 
                target="_blank"><span class="hide">Google</span></a></div>
            </li>
        </ul>
        </div>
		<p class="powered-by">
			<@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a>
		</p>
	</footer>
</div>

${theme.include(body_bottom_include)}

${theme.include(bottom_include)}

</body>

</html>