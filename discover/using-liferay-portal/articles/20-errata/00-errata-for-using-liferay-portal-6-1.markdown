# Errata for Using Liferay Portal 6.1

## Page 75: Propagating changes from site templates to sites

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

## Page 123: Microsoft Office Integration

The Microsoft Office integration features are supported only on Windows XP with
32-bit versions of Microsoft Internet Explorer and 32-bit versions of Microsoft
Office.

## Page 665: Configuring Liferay for High Availability

In the *Properties File Changes* subsection of the chapter *19.2 Performance 
Tuning* section, text for the `last.modified.check` property has been removed
because the property is obsolete.

