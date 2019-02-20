# Localizing JSF Portlets [](id=localizing-jsf-portlets)

In this tutorial, you'll learn how to internationalize your JSF portlet. If
you're unfamiliar with the term localization, you can learn the basics about it
[here](http://en.wikipedia.org/wiki/Internationalization_and_localization).

The recommended way to handle internationalization with JSF portlets is
demonstrated in the [jsf-registration-portlet](https://github.com/liferay/liferay-faces-portal/blob/2.0.0/demo/jsf-registration-portlet)
demo. This method integrates JSF and Liferay very well, because it allows you to
"hook" into thousands of existing internationalized keys that Liferay Portal
includes, and allows you to add your own keys. The following steps demonstrate
how to do this:
   
1. Create a [language properties hook](/develop/tutorials/-/knowledge_base/6-2/overriding-language-properties-using-a-hook)
   inside your portlet plugin and make sure to add a `language-properties`
   element for each locale that your portlet supports. A sample
   `liferay-hook.xml` file that hooks into a Liferay Portal language properties
   file can be found in the jsf-registration-portlet
   [here](https://github.com/liferay/liferay-faces-portal/blob/2.0.0/demo/jsf-registration-portlet/src/main/webapp/WEB-INF/liferay-hook.xml).

2. Create internationalized Langauge properties files for each locale and add
   your key/value pairs to them. You can view a sample
   `Language_en_US.properties` [here](https://github.com/liferay/liferay-faces-portal/blob/2.0.0/demo/jsf-registration-portlet/src/main/resources/Language_en_US.properties).

3. Use the built-in *i18n* keyword Expression Language (EL) in your Facelet
   view. For example, for an `<alloy:form> element` declaring the header title as
   the variable `new-user-registration`, the declaration would look like this:

        <alloy:form id="f">
            <h1 class="header-title">#{i18n['new-user-registration']}</h1>

            ...

        </alloy:form>

    As an example of using the built-in i18n keyword, you can refer to example
    XHTML file [`registrant.xhtml`](https://github.com/liferay/liferay-faces-portal/blob/2.0.0/demo/jsf-registration-portlet/src/main/webapp/WEB-INF/views/registrant.xhtml)
    from demonstration portlet jsf-registration-portlet.

Internationalizing your portlets is especially easy to do using the options that
Liferay Faces provides. Awesome! You now know how to internationalize your JSF
portlet. 

## Related Topics [](id=related-topics)

[Liferay Faces Alloy UI Components](/develop/tutorials/-/knowledge_base/6-2/liferay-faces-alloy-ui-components)

[Liferay Faces Bridge UI Components](/develop/tutorials/-/knowledge_base/6-2/liferay-faces-bridge-ui-components)

[Understanding Liferay Faces Bridge](/develop/tutorials/-/knowledge_base/6-2/understanding-liferay-faces-bridge)

[Understanding Liferay Faces Portal](/develop/tutorials/-/knowledge_base/6-2/understanding-liferay-faces-portal)

[Understanding Liferay Faces Alloy](/develop/tutorials/-/knowledge_base/6-2/understanding-liferay-faces-alloy)
