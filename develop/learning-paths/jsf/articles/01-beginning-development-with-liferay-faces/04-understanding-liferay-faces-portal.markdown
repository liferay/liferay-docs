# Understanding Liferay Faces Portal

Similar to Liferay Faces Bridge, *Liferay Faces Portal* is included in a JSF
portlet project when created using Liferay IDE/Developer Studio. Recall Figure 7
and notice you also have a `liferay-faces-portal-[version].jar` in your Ivy
library. 

Liferay Faces Portal is a JAR that JSF developers can add as a dependency to
their portlet WAR projects in order to utilize Liferay-specific utilities and UI
components. Some of the features included in Liferay Faces Portal are: 

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

Now that you have the fundamentals of using JSF portlets in Liferay, you'll move
on to creating your JSF guestbook portlet. First, you'll generate a persistence
layer for the JSF guestbook using Service Builder. 
