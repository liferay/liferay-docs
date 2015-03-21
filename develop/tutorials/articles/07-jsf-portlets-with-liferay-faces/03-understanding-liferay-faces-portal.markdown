# Understanding Liferay Faces Portal [](id=understanding-liferay-faces-portal)

*Liferay Faces Portal* is distributed in a `.jar` file. You can add Liferay
Faces Portal as a dependency for your portlet projects, in order to use its
Liferay-specific utilities and UI components. Liferay Faces Portal is included
in a JSF portlet project automatically when created using Liferay IDE/Developer
Studio. Examine Figure 1 below and notice you have a
`liferay-faces-portal-[version].jar` in your Ivy library. 

![Figure 1: Ivy downloads the required `.jar` files for your JSF portlet, depending on the JSF UI Component Suite you selected.](../../images/jsf-jars-package-explorer.png)

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

**Related Topics**

[Creating and Deploying JSF Portlets](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/creating-and-deploying-jsf-portlets)

[Developing Liferay Faces Portlets with Maven](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/developing-liferay-faces-portlets-with-maven)

[Understanding Liferay Faces Bridge](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-bridge)

[Understanding Liferay Faces Alloy](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-alloy)

