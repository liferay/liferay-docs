# Upgrading a Liferay JSF Portlet

Liferay JSF portlets are easy to upgrade and require very few changes. The ease
of upgrading JSF portlets is caused by the way JSF portlets run in @product@.
Since they leverage the
[Liferay Faces](/develop/reference/-/knowledge_base/7-0/liferay-faces) project,
your JSF portlet does not directly deal with @project@ code, but rather, it
leverages the Faces JARs that map the JSF lifecycle to the @product@ portlet
lifecycle. Because of this, the only modifications you need to make in your
legacy portlet are dependency updates. There are two ways you can find your JSF
portlet's required dependencies for @product-ver@:

- Visit [http://liferayfaces.org/](http://liferayfaces.org/). This site lets you
  generate the necessary dependencies for a JSF portlet based on a chosen
  @product@ version, JSF version, and component suite.
- Reference the [Liferay Faces Version Scheme](/develop/reference/-/knowledge_base/7-0/liferay-faces-version-scheme)
  article to find the project dependencies corresponding to the @product@
  versions.

In this tutorial, you'll see just how easy it is to upgrade a Liferay JSF
portlet by exploring the upgrade process for the sample JSF Applicant portlet.
This portlet provides fields for a job application.

![Figure 1: The JSF Applicant portlet provides a job application for users to submit.](../../../../images/jsf-applicant-6-2.png)

You can download the
[JSF Applicant Upgrade project]() to view the Liferay Portal
6.2 and @product-ver@ portlets versions. This sample project uses Maven.








