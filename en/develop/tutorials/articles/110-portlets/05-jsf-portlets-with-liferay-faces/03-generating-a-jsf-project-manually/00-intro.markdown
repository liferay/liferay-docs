---
header-id: creating-a-jsf-project-manually
---

# Creating a JSF Project Manually

[TOC levels=1-4]

@product@'s modular architecture lends itself well to modular applications
created using a multitude of different technologies. JSF applications are no
different and can be developed to integrate seamlessly into the Liferay
platform. 

In this section of tutorials, you'll step through packaging and creating a JSF
application that is deployable as an OSGi module at runtime. 

The steps you'll take are outlined below:

- Construct the WAR-style folder structure.
- Specify the necessary dependencies in a build file of your choice.
- Create JSF portlet descriptors and Liferay descriptors.
- Add resource files in the two designated `resources` folders.
- Define the portlet's behavior using a Java class.
- Design a view XHTML form to let the user interact with the portlet.

You can examine the example JSF application solution any time during this
section by downloading its [ZIP
file](https://portal.liferay.dev/documents/113763090/114000653/hello-user-jsf-portlet.zip).

Let's get started!
