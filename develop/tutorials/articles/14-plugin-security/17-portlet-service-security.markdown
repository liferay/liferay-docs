# Portlet Service Security [](id=portlet-service-security)

For each portlet the plugin accesses, replicate this property substituting
`some-portlet` in the `[` square brackets `]` with the name of the accessible
portlet. 

Specify portlet service classes and/or methods the plugin is permitted to
access. Use the `#` character as a delimiter between a class and its method. 

*Example:*

	security-manager-services[some-portlet]=\
		com.liferay.chat.service.EntryLocalService,\
		com.liferay.chat.service.StatusLocalService#getStatuses
