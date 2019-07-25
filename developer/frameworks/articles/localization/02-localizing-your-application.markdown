---
header-id: localizing-your-application
---

# Localizing Your Application

[TOC levels=1-4]

Liferay's localization framework helps you create and use localized messages
in minutes. You create your messages in a default properties file called
`Language.properties` and localize them in properties files that use the
convention `Language_xx.properties`, where `xx` is the locale code. After
deploying your app, the messages are available to your templates. Liferay's JSP
tags, such as `<liferay-ui:message .../>` display them in the user's current
locale automatically, without requiring you to access
`ResourceBundle` or `Locale` objects explicitly. Here are the steps: 

1.  Create a default language properties file called `Language.properties` in 
    your project's resource bundle folder. 

    | Project Type              | Resource Bundle Folder |
    | ------------------------- | ---------------------- |
    | Bean Portlet              | `src/main/resources/content/` |
    | JSF Portlet               | `src/main/resources/` |
    | Liferay MVC Portlet       | `src/main/resources/content/` |
    | PortletMVC4Spring Portlet | `src/main/java/resources/content/` |
    | Angular Widget            | `features/localization/` |
    | React Widget              | `features/localization/` |
    | Vue.js Widget             | `features/localization/` |

2.  Specify your language properties (language keys) using key/value pairs. For 
    example, create a friendly greeting. 

    ```properties
    howdy-partner=Howdy, Partner!
    ```

3.  Configure your app's resource bundle and the locales you're supporting. The 
    locales your @product@ instance supports are specified in its [portal
    properties](/docs/7-2/deploy/-/knowledge_base/d/portal-properties) file
    (here are @product@'s [default
    locales]((@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#Languages%20and%20Time%20Zones)).
    For example, these configurations support translations for English and
    Spanish locales: 

    **`@PortletConfiguration` class annotation:** Can be used in Portlet 3.0
    portlets such as Bean Portlets.

    ```java
    @PortletConfiguration (
        ...
        resourceBundle="content.Language",
        supportedLocales = {"en", "es"}
    )
    ```

    **Portlet descriptor `portlet.xml`:** Can be used in any portlet WAR. 

    ```xml 
    <portlet>
    ...
    <supported-locale>en</supported-locale>
    <supported-locale>es</supported-locale>
    <resource-bundle>content.Language</resource-bundle>
    ...
    </portlet>
    ```

    **`@Component` class annotation:** Can be used in a portlet module such as a
    Liferay MVC Portlet. 

    ```java
    @Component (
        ...
        property = {
            ...
            "javax.portlet.supported-locale=en",
            "javax.portlet.supported-locale=es",
            "javax.portlet.resource-bundle=content.Language"
        }
    )
    ```

4.  Create language properties for a locale. For demonstration purposes, create 
    one manually. [Automatically generating
    translations](/docs/7-2/frameworks/-/knowledge_base/f/automatically-generating-translations)
    is discussed later.

    For example, create a Spanish translation by copying `Language.properties`
    to `Language_es.properties` and translating the property values to Spanish. 

    ```properties 
    howdy-partner=Hola, Compañero!
    ```

5.  In your front-end template code, use the language property. For example, a 
    JSP could use the `howdy-partner` property via the
    [`<liferay-ui:message />`](@platform-ref@/7.2-latest/taglibs/util-taglib/liferay-ui/message.html) tag. 

    ```markup
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %> 
    ...
    <liferay-ui:message key="howdy-partner" />
    ...
    ```

    | **Tip:** The
    | [`liferay-ui`](@platform-ref@/7.2-latest/taglibs/util-taglib/liferay-ui/tld-summary.html)
    | tag library has several tags (e.g., `alert`, `error`, and `message`) that
    | accept language keys. 

6.  Deploy your application and view it in different locales. For example, you 
    could view the app locally in Spanish by specifying the `es` locale code in
    the URL (e.g., `http://localhost:8080/es/...`). 

Congratulations on a great start to localizing your application!

Next, you can explore [generating translations automatically](/docs/7-2/frameworks/-/knowledge_base/f/automatically-generating-translations) 
or [create a language module](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-language-module) 
for using language keys across applications. 

## Related Topics 

[Automatically Generating Translations](/docs/7-2/frameworks/-/knowledge_base/f/automatically-generating-translations)

[Using Language Modules](/docs/7-2/frameworks/-/knowledge_base/f/using-a-language-module)

[Using @product@'s Language Settings](/docs/7-2/frameworks/-/knowledge_base/f//docs/7-1/tutorials/-/knowledge_base/t/using-liferays-language-settings)
