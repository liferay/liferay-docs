# Hook Security [](id=hook-security)

Set to `true` if the hook plugin is permitted use custom JSPs. By default, the
hook plugin is not permitted to use custom JSPs. 

*Example:*

	security-manager-hook-custom-jsp-dir-enabled=false

Specify entities for which the hook plugin is permitted to customize indexing.
Customizing the indexing can involve modifying the search, summary and/or
queries for these entities. 

*Example:*

	security-manager-hook-indexers=\
		com.liferay.portal.model.User

Specify which language property locales the plugin is permitted to override. 

*Example:*

This example grants the plugin permission to override the Great Britain locale
of English, the Spain locale of Spanish, and *all* Portuguese locales. 

	security-manager-hook-language-properties-locales=\
		en_GB,\
		es_ES,\
		pt

Specify which portal properties the plugin is permitted to override. 

*Example:*

	security-manager-hook-portal-properties-keys=\
		phone.number.format.impl

Specify which services the plugin is permitted to access. 

*Example:*

	security-manager-hook-services=\
		com.liferay.portlet.blogs.service.BlogsEntryLocalService

Specify whether to allow the plugin's servlet filter hooks. Setting this to
`true` gives the plugin permission to pre-process requests going to the portal's
servlets and post-process requests coming from the portal's servlets. By
default, the plugin's servlet filter hooks are not allowed. 

	security-manager-hook-servlet-filters-enabled=false

Specify struts action paths that the hook plugin is permitted to add or
override. 

*Example:*

	security-manager-hook-struts-action-paths=\
		/portal/test/pacl/success
