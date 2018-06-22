# Creating the service.xml File [](id=creating-the-service-xml-file)

To define a service for your portlet project, you must create a `service.xml`
file. The DTD (Document Type Declaration) file
[liferay-service-builder_7_1_0.dtd](https://docs.liferay.com/ce/portal/7.1-latest/definitions/liferay-service-builder_7_1_0.dtd.html)
specifies the format and requirements of the XML to use. You can create your
`service.xml` file manually, or you can use Liferay @ide@. @ide@ helps you
build the `service.xml` file piece-by-piece, taking the guesswork out of
creating XML that adheres to the DTD.

If you created your project from the Blade or @ide@ template, you have
a `service.xml` file in your `*-service` module's root folder with
an `entity` element named *Foo*. Remove the entire `<entity name="Foo" ...> ...
</entity>` element: it's just an example. It has no practical use for you. 

If you don't already have a `service.xml` file, create one in your `*-service`
module's root folder and open the file. Liferay @ide@ provides a Diagram mode
and a Source mode to give you different perspectives of the service information
in your `service.xml` file.

- **Diagram mode** facilitates creating and visualizing relationships between 
  service entities.
- **Source mode** brings up the `service.xml` file's raw XML content in the 
  editor.

You can switch between these modes.

Next, you'll specify your service's global information. 
