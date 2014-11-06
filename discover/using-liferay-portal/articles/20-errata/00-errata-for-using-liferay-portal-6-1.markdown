# Errata for Using Liferay Portal 6.1

## Page 123: Microsoft Office Integration

The Microsoft Office integration features are supported only on Windows XP with
32-bit versions of Microsoft Internet Explorer and 32-bit versions of Microsoft
Office.

## Page 507: Installing Liferay on Tomcat 7

It is recommended to also add the `support-tomcat.jar` file to the
`$TOMCAT_HOME/lib/ext` folder of your Tomcat installation. The file can be
downloaded from
[search.maven.org/remotecontent?filepath=com/liferay/portal/support-tomcat/6.1.2/support-tomcat-6.1.2.jar](search.maven.org/remotecontent?filepath=com/liferay/portal/support-tomcat/6.1.2/support-tomcat-6.1.2.jar).
This `JAR` provides classes that extend some Tomcat-specific classes in order to
support Liferay's runtime.

## Page 508: Installing Liferay on Tomcat 7

The note explaining that Tomcat 6 users should not copy the `ccpp.jar` is no
longer applicable to this section. The runtime error caused by this `JAR` was
resolved in the release of Liferay 6.0.

## Page 665: Configuring Liferay for High Availability

In the *Properties File Changes* subsection of the chapter *19.2 Performance 
Tuning* section, text for the `last.modified.check` property has been removed
because the property is obsolete.

