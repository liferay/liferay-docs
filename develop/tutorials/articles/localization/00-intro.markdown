# Localization [](id=localization)

If your portlets target an international audience, you can localize your
portlets' user interfaces. To localize a portlet, you need to create language
properties files, also called resource bundles, for each language you wish to
support. You can translate language properties manually or use a web service to
translate them for you. Conveniently, all of the translated messages used by
Liferay Portal are also accessible to plugin projects. To localize messages in
addition to portal's localized messages, you must create language keys in one or
more resource bundles within your plugin project. When planning your portlet's
localization, you should consider the following questions.

Are there messages that Portal uses that you'd like to use in your portlets?
Does your plugin contain multiple portlets? If so, do any of its portlets need
to be available for administrative purposes in the Control Panel? If any of its
portlets need to be in the Control Panel, you should create separate resource
bundles for each of these portlets. Otherwise, your portlets should share the
same resource bundle so that you can leverage Liferay's language building
capabilities from Liferay IDE and the Plugins SDK. You'll learn how to
localize your portlets in all of these scenarios.

**Related Topics**

[Localizing JSF Portlets](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/localizing-jsf-portlets)
