# Localizing JSF Portlets [](id=localizing-jsf-portlets)

In this tutorial, you'll learn how to internationalize your JSF portlet. If
you're unfamiliar with the term localization, you can learn the basics about it
[here](http://en.wikipedia.org/wiki/Internationalization_and_localization).

There are at least two ways to implement internationalization with JSF portlets in
Liferay Portal. One way is to use the standard JSF mechanism to create your own
*i18n* keyword; the other way is to use the built-in *i18n* keyword provided by
[Liferay Faces Portal](http://www.liferay.com/community/liferay-projects/liferay-faces/portal).

## Option 1: Using the Standard JSF Mechanism to Create Your Own i18n Keyword [](id=option-1-using-the-standard-jsf-mechanism-to-create-your-own-i18n-keyword)

The following steps demonstrate internationalizing a portlet using the standard
JSF mechanism. 

1. In your classpath, add a properties file for each locale you want to support.

    You can, for example, call your default properties file `i18n.properties`
    and put it in a folder that's in your plugin project's classpath. In a Maven
    environment, you can put the properties files in your `src/main/resources`
    folder. For each locale in addition to your default
    locale, add a properties file named using the convention
    `[base-name]_[locale-code].properties`. You can refer to the sample default
    properties file
    [`i18n.properties`](https://github.com/liferay/liferay-faces/blob/master/demos/bridge/jsf2-portlet/src/main/resources/i18n.properties).

2. Add entries to the `application` element in your portlet's `faces-config.xml`
   file to configure the locales and to configure the properties files as
   resource bundles. In a Maven plugin project the `faces-config.xml` file
   is in the `src/main/webapp/WEB-INF/` folder.

     Here's an `application` element, for example, with English as its default
     locale and Spanish as a supported locale:

        <application>
          <locale-config>
             <default-locale>en</default-locale>
             <supported-locale>es</supported-locale>
          </locale-config>
          <resource-bundle>
             <base-name>i18n</base-name>
             <var>i18n</var>
          </resource-bundle>
        </application>

    In this descriptor, the resource bundle specifies a
    base name value *i18n* to map to the properties files that have names
    starting with *i18n*. It specifies *i18n* as the custom keyword (or
    variable) for the resource bundle.
     
    An example resource bundle declaration can be found in the sample
    jsf2-portlet's [`faces-config.xml`](https://github.com/liferay/liferay-faces/blob/master/demos/bridge/jsf2-portlet/src/main/webapp/WEB-INF/faces-config.xml).

3. Use your custom keyword in Expression Language (EL) in your Facelet view, to
   map to your property files' language keys.

    An `aui:field` element for a first name, for example, would specify the
    custom keyword *i18n*, like in the following code snippet: 

        <aui:field id="firstNameField" label="#{i18n['first-name']}">

            ...

        </aui:field>

    To see an example of using an i18n keyword, refer to the
    [`applicant.xhtml`](https://github.com/liferay/liferay-faces/blob/master/demos/bridge/jsf2-portlet/src/main/webapp/views/applicant.xhtml)
    file in the jsf2-portlet.

You may find the next way of implementing i18n easier and more powerful.

## Option 2: Using the Built-in *i18n* keyword provided by Liferay Faces Portal [](id=option-2-using-the-built-in-i18n-keyword-provided-by-liferay-faces-portal)

The other way to handle internationalization with JSF portlets as shown in the
 [jsf2-registration-portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-registration-portlet)
demo. This method integrates JSF and Liferay very well, because it allows you to
"hook" into thousands of existing internationalized keys that Liferay Portal
includes, and allows you to add your own keys. The following steps demonstrate
how to do this:
   
1. Create a [language properties hook](/develop/tutorials/-/knowledge_base/6-2/overriding-language-properties-using-a-hook)
   inside your portlet plugin and make sure to add a `language-properties`
   element for each locale that your portlet supports. A sample
   `liferay-hook.xml` file that hooks into a Liferay Portal language properties
   file can be found in the jsf2-registration-portlet
   [here](https://github.com/liferay/liferay-faces/blob/master/demos/portal/jsf2-registration-portlet/src/main/webapp/WEB-INF/liferay-hook.xml).

2. Create internationalized Langauge properties files for each locale and add
   your key/value pairs to them. You can view a sample
   `Language_en_US.properties` [here](https://github.com/liferay/liferay-faces/blob/master/demos/portal/jsf2-registration-portlet/src/main/resources/Language_en_US.properties).

3. Use the built-in *i18n* keyword Expression Language (EL) in your Facelet
   view. For example, for an `<aui:form> element` declaring the header title as
   the variable `new-user-registration`, the declaration would look like this:

        <aui:form id="f">
            <h1 class="header-title">#{i18n['new-user-registration']}</h1>

            ...

        </aui:form>

    As an example of using the built-in i18n keyword, you can refer to example
    XHTML file [`registrant.xhtml`](https://github.com/liferay/liferay-faces/blob/master/demos/portal/jsf2-registration-portlet/src/main/webapp/views/registrant.xhtml)
    from demonstration portlet jsf2-registration-portlet.

Internationalizing your portlets is especially easy to do using the options that
Liferay Faces provides. Awesome! You now know how to internationalize your JSF
portlet. 

**Related Topics**

[Liferay Faces Alloy UI Components](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/liferay-faces-alloy-ui-components)

[Liferay Faces Bridge UI Components](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/liferay-faces-bridge-ui-components)

[Understanding Liferay Faces Bridge](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-bridge)

[Understanding Liferay Faces Portal](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-portal)

[Understanding Liferay Faces Alloy](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-alloy)
