---
header-id: creating-the-service-xml-file
---

# Creating the service.xml File

[TOC levels=1-4]

To define a service for your portlet project, you must create a `service.xml`
file. The DTD (Document Type Declaration) file
[liferay-service-builder_7_2_0.dtd](https://docs.liferay.com/ce/portal/7.2-latest/definitions/liferay-service-builder_7_2_0.dtd.html)
specifies the format and requirements of the XML to use. 

A `service.xml` was created for you when you [created your Service Builder project](/docs/7-2/appdev/-/knowledge_base/creating-a-service-builder-project). 
It's in your `*-service` module's root folder with an `entity` element named
*Foo*. This is (obviously) an example entity, but you can use it as a pattern
for creating your own. 

[Liferay @ide@](/docs/7-2/reference/-/knowledge_base/r/liferay-dev-studio) 
provides a Diagram mode and a Source mode to give you different
perspectives of the service information in your `service.xml` file.

- **Diagram mode** facilitates creating and visualizing relationships between 
  service entities.
- **Source mode** brings up the `service.xml` file's raw XML content in the 
  editor.

If you use Liferay @ide@, you can switch between these modes. Of course, you
don't have to use Liferay @ide@ to work on Liferay projects. 

Next, you'll specify your service's global information. 
