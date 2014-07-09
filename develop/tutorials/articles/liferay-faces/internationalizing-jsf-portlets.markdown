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

3. Use your custom *i18n* keyword Expression Language (EL) in your Facelet view.
   For example, for an `<aui:field> element` declaring the first name, the label
   attribute would specify the *i18n* keyword, like the following:
   
        <aui:field id="firstNameField" label="#{i18n['first-name']}">

            ...

        </aui:field>

    You can view an example XHTML file accomplishing this in the
    [`applicant.xhtml`](https://github.com/liferay/liferay-faces/blob/master/demos/bridge/jsf2-portlet/src/main/webapp/views/applicant.xhtml)
    file in the jsf2-portlet for further reference.

The other way to handle internationalization with JSF portlets is to use the
built-in *i18n* keyword provided by [Liferay Faces
Portal](http://www.liferay.com/community/liferay-projects/liferay-faces/portal),
as shown in the
[jsf2-registration-portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-registration-portlet)
demo. This method integrates JSF and Liferay very well, because it allows you to
"hook" into thousands of existing internationalized keys that Liferay Portal
includes, and allows you to add your own keys. The following steps demonstrate
how to do this:
   
1. Create a hook inside your portlet plugin and make sure the following
   declarations are specified in the `liferay-hook.xml` file:

        <hook>
            <portal-properties>portal.properties</portal-properties>
            <language-properties>Language_en_US.properties</language-properties>
        </hook>

    A sample `liferay-hook.xml` file that uses these declarations can be found
    in the jsf2-registration-portlet
    [here](https://github.com/liferay/liferay-faces/blob/master/demos/portal/jsf2-registration-portlet/src/main/webapp/WEB-INF/liferay-hook.xml).

2. Create internationalized Langauge properties files for each language. In the
   last step you specified the language properties file for English, therefore,
   you'd need to create that file and populate it with translations. The
   language properties files you create for each language should also be
   specified in the `liferay-hook.xml` file. You can view a sample
   `Language_en_US.properties`
   [here](https://github.com/liferay/liferay-faces/blob/master/demos/portal/jsf2-registration-portlet/src/main/resources/Language_en_US.properties).

3. Use the built-in *i18n* keyword Expression Language (EL) in your Facelet
   view. For example, for an `<aui:form> element` declaring the header title as
   the variable `new-user-registration`, the declaration would appear as the
   following:
   
        <aui:form id="f">
		    <h1 class="header-title">#{i18n['new-user-registration']}</h1>

            ...

        </aui:form>

    You can view an example XHTML file accomplishing this in the sample
    [`registrant.xhtml`](https://github.com/liferay/liferay-faces/blob/master/demos/portal/jsf2-registration-portlet/src/main/webapp/views/registrant.xhtml)
    file in the jsf2-registration-portlet for further reference.

Internationalizing your portlets is especially easy to do using the options that
Liferay Faces provides. Awesome! You now know how to internationalize your JSF
portlet.
