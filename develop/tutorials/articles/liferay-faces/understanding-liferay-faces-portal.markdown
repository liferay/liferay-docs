# Understanding Liferay Faces Portal [](id=understanding-liferay-faces-portal)

*Liferay Faces Portal* is a JAR that JSF developers can add as a dependency to
their portlet WAR projects in order to utilize Liferay-specific utilities and UI
components. Liferay Faces Portal is included in a JSF portlet project
automatically when created using Liferay IDE/Developer Studio. Examine Figure 1
below and notice you have a `liferay-faces-portal-[version].jar` in your Ivy
library. 

![Figure 1: Ivy downloads the required JARs for your JSF portlet, depending on the JSF UI Component Suite you selected.](../../images/jsf-jars-package-explorer.png)

Some of the features included in Liferay Faces Portal are: 

- Utilities: Provides the `LiferayFacesContext` which extends the `FacesContext`
with a variety of Portlet-API and Liferay-specific convenience methods. You can
learn more about the `LiferayFacesContext` by visiting its dedicated
[tutorial](/develop/tutorials/-/knowledge_base/6-2/using-the-liferayfacescontext-with-liferay-faces-portal). 

- Facelet Composite Components: Provides a set of JSF equivalents for
`liferay-ui:captcha`, `liferay-ui:icon`, `liferay-ui:input-editor`, etc. 

<!-- The `liferay-ui` tags are being deprecated on the next release of Liferay
Faces and will be replaced by `portal:` components. Make sure to replaces the
above bullet once it is released. -Cody -->

- Expression Language: Adds a set of EL keywords such as `liferay` for getting
Liferay-specific info, and `i18n` for integration with out-of-the-box Liferay
internationalized messages. 

Great! You now have an understanding of what Liferay Faces Portal is, and what
it accomplishes in your JSF application. 

## Related Topics [](id=related-topics)

[Understanding Liferay Faces Alloy](/develop/tutorials/-/knowledge_base/6-2/understanding-liferay-faces-alloy)

[Understanding Liferay Faces Portal](/develop/tutorials/-/knowledge_base/6-2/understanding-liferay-faces-portal)

[Accessing the Portlet API with ExternalContext](/develop/tutorials/-/knowledge_base/6-2/accessing-the-portlet-api-with-externalcontext)

