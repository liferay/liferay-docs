# Internationalizing JSF Portlets

There are at least two ways to handle internationalization with JSF portlets in
Liferay Portal: 

1. Using the standard JSF mechanism to create your own *i18n* keyword, as shown in the [jsf2-portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-portlet)
   demo.
    - Create a properties file in the classpath like
    [`i18n.properties`](https://github.com/liferay/liferay-faces/blob/master/demos/bridge/jsf2-portlet/src/main/resources/i18n.properties)
    - Create a `<resource-bundle>` entry in the `<faces-config>` element, as
    demonstrated in
    [`faces-config.xml`](https://github.com/liferay/liferay-faces/blob/master/demos/bridge/jsf2-portlet/src/main/webapp/WEB-INF/faces-config.xml)
    - Use your custom *i18n* keyword Expression Language (EL) in your Facelet
    view, like [`applicant.xhtml`](https://github.com/liferay/liferay-faces/blob/master/demos/bridge/jsf2-portlet/src/main/webapp/views/applicant.xhtml)

2. Using the built-in *i18n* keyword provided by [Liferay Faces
   Portal](http://www.liferay.com/community/liferay-projects/liferay-faces/portal),
   as shown in the
   [jsf2-registration-portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-registration-portlet)
   demo. This method integrates JSF and Liferay very well, because it allows you
   to "hook" into thousands of existing internationalized keys that Liferay
   Portal includes and allows you to add your own keys. 
    - Create a hook, like
    [`liferay-hook.xml`](https://github.com/liferay/liferay-faces/blob/master/demos/portal/jsf2-registration-portlet/src/main/webapp/WEB-INF/liferay-hook.xml),
    inside your portlet plugin
    - Create internationalized Langauge properties files, like
    [`Language_en_US.properties`](https://github.com/liferay/liferay-faces/blob/master/demos/portal/jsf2-registration-portlet/src/main/resources/Language_en_US.properties) 
    - Use the built-in *i18n* keyword Expression Language (EL) in your Facelet
    view, like
    [`registrant.xhtml`](https://github.com/liferay/liferay-faces/blob/master/demos/portal/jsf2-registration-portlet/src/main/webapp/views/registrant.xhtml) 

<!-- This section on JSF portlet i18n needs to be refactored to demonstrate,
with code, how to internationalize a JSF portlet. - Jim -->

Internationalizing your portlets is especially easy to do using the options that
Liferay Faces provides. 

Next, we'll learn how to communicate between JSF portlets using IPC. 

## Next Steps

<!-- Add once JSF tutorials are finished. -Cody -->
