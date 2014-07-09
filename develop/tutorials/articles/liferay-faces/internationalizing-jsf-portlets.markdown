# Internationalizing JSF Portlets

In this tutorial, you'll learn how to internationalize your JSF portlet. If
you're unfamiliar with the term *internationalize*, you can learn about the
basics
[here](http://en.wikipedia.org/wiki/Internationalization_and_localization).

There are at least two ways to handle internationalization with JSF portlets in
Liferay Portal. The first demonstration describes using the standard JSF
mechanism to create your own *i18n* keyword, as shown in the
[jsf2-portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-portlet)
demo.

1. Create a properties file in the `src/main/resources` directory named
   `i18n.properties` and populate the file with translations. You can visit the
   sample
   [`i18n.properties`](https://github.com/liferay/liferay-faces/blob/master/demos/bridge/jsf2-portlet/src/main/resources/i18n.properties)
   file for a good reference.

2. Create a `<resource-bundle>` entry in the `<faces-config>` element in the
   `src/main/webapp/WEB-INF/faces-config.xml` file, like the following:

        <resource-bundle>
            <base-name>i18n</base-name>
            <var>i18n</var>
        </resource-bundle>

    An example of this declaration can be found in the sample jsf2-portlet's
    [`faces-config.xml`](https://github.com/liferay/liferay-faces/blob/master/demos/bridge/jsf2-portlet/src/main/webapp/WEB-INF/faces-config.xml).

3. Use your custom *i18n* keyword Expression Language (EL) in your Facelet view
   located in the `src/main/webapps/views/applicant.xhtml` file. For example,
   for a `aui:field` declaring the first name, the label attribute would specify
   the *i18n* keyword, like the following:
   
        <aui:field id="firstNameField" label="#{i18n['first-name']}">
            ...

    You can view a full
    [`applicant.xhtml`](https://github.com/liferay/liferay-faces/blob/master/demos/bridge/jsf2-portlet/src/main/webapp/views/applicant.xhtml)
    in the jsf2-portlet for further reference.

The other way to handle internationalization with JSF portlets is to use the
built-in *i18n* keyword provided by [Liferay Faces
Portal](http://www.liferay.com/community/liferay-projects/liferay-faces/portal),
as shown in the
[jsf2-registration-portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-registration-portlet)
demo. This method integrates JSF and Liferay very well, because it allows you to
"hook" into thousands of existing internationalized keys that Liferay Portal
includes, and allows you to add your own keys. The following steps demonstrates
how to do this:
   
1. Create a hook, like
   [`liferay-hook.xml`](https://github.com/liferay/liferay-faces/blob/master/demos/portal/jsf2-registration-portlet/src/main/webapp/WEB-INF/liferay-hook.xml),
   inside your portlet plugin
2. Create internationalized Langauge properties files, like
   [`Language_en_US.properties`](https://github.com/liferay/liferay-faces/blob/master/demos/portal/jsf2-registration-portlet/src/main/resources/Language_en_US.properties)
3. Use the built-in *i18n* keyword Expression Language (EL) in your Facelet
   view, like
   [`registrant.xhtml`](https://github.com/liferay/liferay-faces/blob/master/demos/portal/jsf2-registration-portlet/src/main/webapp/views/registrant.xhtml)

<!-- This section on JSF portlet i18n needs to be refactored to demonstrate,
with code, how to internationalize a JSF portlet. - Jim -->

Internationalizing your portlets is especially easy to do using the options that
Liferay Faces provides. 

## Next Steps

<!-- Add once JSF tutorials are finished. -Cody -->
