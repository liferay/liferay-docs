# Understanding Liferay Faces Portal [](id=understanding-liferay-faces-portal)

*Liferay Faces Portal* is distributed in a `.jar` file. You can add Liferay
Faces Portal as a dependency for your portlet projects to use its
Liferay-specific utilities and UI components. When Liferay Faces Portal is
included in a JSF portlet project, the `com.liferay.faces.portal.[version].jar`
file resides in the portlet's library. 

![Figure 1: The required `.jar` files are downloaded for your JSF portlet based on the JSF UI Component Suite you configured.](../../images/jsf-jars-package-explorer.png)

Some of the features included in Liferay Faces Portal are: 

- Utilities: Provides the `LiferayPortletHelperUtil` which contains a variety
Portlet-API and Liferay-specific convenience methods.

- JSF Components: Provides a set of JSF equivalents for
`liferay-ui:captcha`, `liferay-ui:icon`, `liferay-ui:input-editor`, etc. 

- Expression Language: Adds a set of EL keywords such as `liferay` for getting
Liferay-specific info, and `i18n` for integration with out-of-the-box Liferay
internationalized messages. 

Great! You now have an understanding of what Liferay Faces Portal is, and what
it accomplishes in your JSF application. 

## Related Topics [](id=related-topics)

[Creating a JSF Project Manually](/develop/tutorials/-/knowledge_base/7-1/creating-a-jsf-project-manually)

[Understanding Liferay Faces Bridge](/develop/reference/-/knowledge_base/7-1/understanding-liferay-faces-bridge)

[Understanding Liferay Faces Alloy](/develop/reference/-/knowledge_base/7-1/understanding-liferay-faces-alloy)
