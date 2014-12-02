# Errata for Using Liferay Portal 6.1 [](id=errata-for-using-liferay-portal-6-1)

## Page 75: Propagating changes from site templates to sites [](id=page-75-propagating-changes-from-site-templates-to-sites)

In the *Propagating changes from site templates to sites* subsection of the
chapter *3.3 Using Site Templates* section, the following text clarifies how the
feature works and emphasizes its limitations:

**Important: ** Portlet data, related resources, and permissions on resources
are only copied from a site template to a site when that site is *first* created
based on the template. No changes made in a a template's portlet data, related
resources, or permissions are propagated to the site after the site is
created. Neither are such changes propagated to a site by *Reset* or *Reset and
Propagate* features. 

For example, consider a site template administrator including a Message Boards
portlet as part of a site template. She even creates Message Board categories
and configures permissions over the actions of the categories. The first time a
site is created based on the site template, the categories (portlet data) and
related permissions are copied to the site. If the site template administrator
adds, removes, or deletes some categories, however, such changes *aren't*
propagated to the site. 

## Page 123: Microsoft Office Integration [](id=page-123-microsoft-office-integration)

The Microsoft Office integration features are supported only on Windows XP with
32-bit versions of Microsoft Internet Explorer and 32-bit versions of Microsoft
Office.

## Page 507: Installing Liferay on Tomcat 7 [](id=page-507-installing-liferay-on-tomcat-7)

It is recommended to also add the `support-tomcat.jar` file to the
`$TOMCAT_HOME/lib/ext` folder of your Tomcat installation. The file can be
downloaded from
[search.maven.org/remotecontent?filepath=com/liferay/portal/support-tomcat/6.1.2/support-tomcat-6.1.2.jar](search.maven.org/remotecontent?filepath=com/liferay/portal/support-tomcat/6.1.2/support-tomcat-6.1.2.jar).
This `JAR` provides classes that extend some Tomcat-specific classes in order to
support Liferay's runtime.

## Page 508: Installing Liferay on Tomcat 7 [](id=page-508-installing-liferay-on-tomcat-7)

The note explaining that Tomcat 6 users should not copy the `ccpp.jar` is no
longer applicable to this section. The runtime error caused by this `JAR` was
resolved in the release of Liferay 6.0.

## Page 665: Configuring Liferay for High Availability [](id=page-665-configuring-liferay-for-high-availability)

In the *Properties File Changes* subsection of the chapter *19.2 Performance 
Tuning* section, text for the `last.modified.check` property has been removed
because the property is obsolete.

